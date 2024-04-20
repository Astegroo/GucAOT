package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;

import static game.engine.dataloader.DataLoader.readTitanRegistry;

public class Battle {
	private static final int[][] PHASES_APPROACHING_TITANS =
			{
					{1, 1, 1, 2, 1, 3, 4},
					{2, 2, 2, 1, 3, 3, 4},
					{4, 4, 4, 4, 4, 4, 4}
			};
	private static final int WALL_BASE_HEALTH = 10000;

	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;
	private final WeaponFactory weaponFactory;
	private final HashMap<Integer, TitanRegistry> titansArchives;
	private final ArrayList<Titan> approachingTitans;
	private final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes;

	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes,
				  int initialResourcesPerLane) throws IOException
	{
		super();
		this.numberOfTurns = numberOfTurns;
		this.battlePhase = BattlePhase.EARLY;
		this.numberOfTitansPerTurn = 1;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.resourcesGathered = initialResourcesPerLane * initialNumOfLanes;
		this.weaponFactory = new WeaponFactory();
		this.titansArchives = readTitanRegistry();
		this.approachingTitans = new ArrayList<Titan>();
		this.lanes = new PriorityQueue<>();
		this.originalLanes = new ArrayList<>();
		this.initializeLanes(initialNumOfLanes);
	}

	public int getNumberOfTurns() {
		return numberOfTurns;
	}

	public void setNumberOfTurns(int numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}

	public int getResourcesGathered() {
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered) {
		this.resourcesGathered = resourcesGathered;
	}

	public BattlePhase getBattlePhase() {
		return battlePhase;
	}

	public void setBattlePhase(BattlePhase battlePhase) {
		this.battlePhase = battlePhase;
	}

	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}

	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance) {
		this.titanSpawnDistance = titanSpawnDistance;
	}

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}

	public HashMap<Integer, TitanRegistry> getTitansArchives() {
		return titansArchives;
	}

	public ArrayList<Titan> getApproachingTitans() {
		return approachingTitans;
	}

	public PriorityQueue<Lane> getLanes() {
		return lanes;
	}

	public ArrayList<Lane> getOriginalLanes() {
		return originalLanes;
	}

	private void initializeLanes(int numOfLanes)
	{
		for (int i = 0; i < numOfLanes; i++)
		{
			Wall w = new Wall(WALL_BASE_HEALTH);
			Lane l = new Lane(w);

			this.getOriginalLanes().add(l);
			this.getLanes().add(l);
		}
	}

	public void refillApproachingTitans() throws IOException {
		int row;
		if (battlePhase == BattlePhase.EARLY) {
			row = 0;
		} else if (battlePhase == BattlePhase.INTENSE) {
			row = 1;
		} else {
			row = 2;
		}
		HashMap<Integer, TitanRegistry> a = readTitanRegistry();
		for (int i = 0; i < 7; i++) {
			TitanRegistry TitanR = a.get(PHASES_APPROACHING_TITANS[row][i]);
			approachingTitans.add(TitanR.spawnTitan(getTitanSpawnDistance()));
		}
	}

	public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException, IOException {
		if (lane.isLaneLost() || !lanes.contains(lane))
		{
			throw new InvalidLaneException();
		}
		FactoryResponse f = weaponFactory.buyWeapon(resourcesGathered, weaponCode);
		lane.addWeapon(f.getWeapon());
		setResourcesGathered(f.getRemainingResources());
		performTurn();
	}


	public void passTurn() throws IOException {performTurn();}


	private void addTurnTitansToLane() throws IOException
	{
		for (int i = 0; i < numberOfTitansPerTurn; i++)
		{
			if (approachingTitans.isEmpty())
			{
				refillApproachingTitans();
			}
			Lane leastDangerousLane = null;
			for (Lane lane : lanes)
			{
				if (leastDangerousLane == null || lane.getDangerLevel() < leastDangerousLane.getDangerLevel())
				{
					leastDangerousLane = lane;
				}
			}
			if (leastDangerousLane != null)
			{
				leastDangerousLane.addTitan(approachingTitans.remove(0));
			}
		}
	}

	private void moveTitans()
	{
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while (!lanes.isEmpty())
		{
			Lane L = lanes.remove();
			if (!L.isLaneLost())
			{
				L.moveLaneTitans();
			}
			temp.add(L);
		}
		lanes.addAll(temp);
	}

	private int performWeaponsAttacks()
	{
		int totalResources = 0;
		for (Lane lane : lanes)
		{
			totalResources += lane.performLaneWeaponsAttacks();
		}
		this.score += totalResources;
		this.resourcesGathered += totalResources;
		return totalResources;
	}

	private int performTitansAttacks()
	{
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		int resourceSum = 0;
		while (!lanes.isEmpty())
		{
			Lane L = lanes.poll();
			if (!L.isLaneLost())
			{
				int a = L.performLaneTitansAttacks();
				resourceSum += a;
				if (!L.isLaneLost()) temp.add(L);
			}
		}
		lanes.addAll(temp);
		return resourceSum;
	}

	private void updateLanesDangerLevels()
	{
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while (!lanes.isEmpty())
		{
			Lane L = lanes.remove();
			if (!L.isLaneLost())
			{
				L.updateLaneDangerLevel();
			}
			temp.add(L);
		}
		lanes.addAll(temp);
	}

	private void performTurn() throws IOException {
		moveTitans();
		performWeaponsAttacks();
		performTitansAttacks();
		addTurnTitansToLane();
		updateLanesDangerLevels();
		finalizeTurns();
	}


	private void finalizeTurns() {
		numberOfTurns++;
		if (numberOfTurns < 15) setBattlePhase(BattlePhase.EARLY);
		else if (numberOfTurns < 30) setBattlePhase(BattlePhase.INTENSE);
		else setBattlePhase(BattlePhase.GRUMBLING);
		if (numberOfTurns > 30 && numberOfTurns % 5 == 0) setNumberOfTitansPerTurn(getNumberOfTitansPerTurn() * 2);
	}

	public boolean isGameOver()
	{
		PriorityQueue<Lane> temp = new PriorityQueue<>();
		while (!lanes.isEmpty())
		{
			Lane L = lanes.remove();
			temp.add(L);
			if (!L.isLaneLost()) return false;
		}
		lanes.addAll(temp);
		return true;
	}
}