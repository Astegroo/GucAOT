package game.engine;
import java.io.IOException;
import java.util.ArrayList;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;

import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.lanes.Lane;
import game.engine.titans.*;
import game.engine.weapons.factory.WeaponFactory;


public class Battle 
{	
	private static final int[][] PHASES_APPROACHING_TITANS = {
			{ 1, 1, 1, 2, 1, 3, 4 },
			{ 2, 2, 2, 1, 3, 3, 4 },
			{ 4, 4, 4, 4, 4, 4, 4 } 
	}; 

	private final static int WALL_BASE_HEALTH = 10000 ;
	
	private final ArrayList<Titan> approachingTitans = new ArrayList<>();

	private int numberOfTurns;
	
	private int resourcesGathered; 

	private BattlePhase battlePhase = BattlePhase.EARLY;
	
	private int numberOfTitansPerTurn = 1;

	private int score;
	
	private int titanSpawnDistance;
	
	private final WeaponFactory weaponFactory ;

	private final HashMap<Integer,TitanRegistry> titansArchives;

	private final PriorityQueue<Lane> lanes = new PriorityQueue<>();

	private final ArrayList<Lane> originalLanes;

	public static int[][] getPHASES_APPROACHING_TITANS() {
		return PHASES_APPROACHING_TITANS;
	}

	public static int getWALL_BASE_HEALTH() {
		return WALL_BASE_HEALTH;
	}
	
	public static int getWallBaseHealth() {
		return WALL_BASE_HEALTH;
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

	public HashMap<Integer, TitanRegistry> getTitansArchives() 
	{
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

	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcePerLane) throws IOException {
        this.weaponFactory = new WeaponFactory();
        this.numberOfTurns = numberOfTurns;
        this.score = score;
        this.titanSpawnDistance = titanSpawnDistance;
        this.resourcesGathered = initialNumOfLanes * initialResourcePerLane;
        this.titansArchives = DataLoader.readTitanRegistry();
        this.originalLanes = new ArrayList<>();
        initializeLanes(initialNumOfLanes);
    }
	
	private void initializeLanes(int numOfLanes)
	{
		for (int i = 0; i < numOfLanes; i++) 
		{
			Wall w = new Wall(WALL_BASE_HEALTH);
			Lane l = new Lane(w);
			lanes.add(l);
			originalLanes.add(l);
		}
	}

	public static void main(String[] args) {
	}
	
	
	
}