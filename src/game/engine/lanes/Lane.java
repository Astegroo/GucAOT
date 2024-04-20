package game.engine.lanes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.titans.*;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane>
{
	private final Wall laneWall;
	private int dangerLevel;
	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;

	public Lane(Wall laneWall)
	{
		super();
		this.laneWall = laneWall;
		this.dangerLevel = 0;
		this.titans = new PriorityQueue<>();
		this.weapons = new ArrayList<>();
	}

	public Wall getLaneWall()
	{
		return this.laneWall;
	}

	public int getDangerLevel()
	{
		return this.dangerLevel;
	}

	public void setDangerLevel(int dangerLevel)
	{
		this.dangerLevel = dangerLevel;
	}

	public PriorityQueue<Titan> getTitans()
	{
		return this.titans;
	}

	public ArrayList<Weapon> getWeapons()
	{
		return this.weapons;
	}

	@Override
	public int compareTo(Lane o)
	{
		return this.dangerLevel - o.dangerLevel;
	}

	public void addTitan(Titan titan){
		titans.add(titan);
	}

	public void addWeapon(Weapon weapon){
		weapons.add(weapon);
	}

	public void moveLaneTitans()
	{
		if (titans.isEmpty())
			return;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!this.titans.isEmpty())
		{
			Titan x = this.titans.remove();
			x.move();
			temp.add(x);
		}
		titans.addAll(temp);
	}

	public int performLaneTitansAttacks()
	{
		if (titans.isEmpty() || this.isLaneLost()) return 0;
		int resourceSum = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!this.titans.isEmpty())
		{
			Titan x = this.titans.poll();
			if(x.hasReachedTarget()) resourceSum+=x.attack(this.laneWall);
			temp.add(x);
		}
		titans.addAll(temp);
		return resourceSum;
	}
	public int performLaneWeaponsAttacks()
	{
		int totalResources = 0;
		for (Weapon weapon : weapons)
		{
			totalResources += weapon.turnAttack(titans);
		}
		return totalResources;
	}
	public boolean isLaneLost() {return this.laneWall.isDefeated();}
	public void updateLaneDangerLevel()
	{
		int dangerSum=0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!titans.isEmpty())
		{
			Titan x = titans.remove();
			dangerSum+=x.getDangerLevel();
			temp.add(x);
		}
		titans.addAll(temp);
		this.dangerLevel=dangerSum;
	}

}
