package game.engine.lanes;

import java.util.ArrayList;
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
		if (titans.isEmpty())return;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!this.titans.isEmpty())
		{
			Titan titan = this.titans.poll();
			titan.move();
			temp.add(titan);
		}
		while (!temp.isEmpty()) titans.add(temp.remove());
	}

	public int performLaneTitansAttacks()
	{
		if (titans.isEmpty() || this.isLaneLost()) return 0;
		int sum = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!this.titans.isEmpty())
		{
			Titan titan = this.titans.poll();
			if(titan.hasReachedTarget()) sum+=titan.attack(this.laneWall);
			temp.add(titan);
		}
		while (!temp.isEmpty()) titans.add(temp.remove());
		return sum;
	}
	
	public int performLaneWeaponsAttacks()
	{
		int totalResources = 0;
		for (int i =0 ;i<weapons.size();i++)
		{
			totalResources += weapons.get(i).turnAttack(titans);
		}
		return totalResources;
	}
	
	public boolean isLaneLost() {return this.laneWall.isDefeated();}
	
	public void updateLaneDangerLevel()
	{
		int sum =0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while(!titans.isEmpty())
		{
			Titan titan = titans.remove();
			sum +=titan.getDangerLevel();
			temp.add(titan);
		}
		while (!temp.isEmpty()) titans.add(temp.remove());
		this.dangerLevel= sum;
	}

}
