package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class WallTrap extends Weapon
{
	public static final int WEAPON_CODE = 4;

	public WallTrap(int baseDamage)
	{
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans)
	{
		if (laneTitans.isEmpty()) return 0;
		Titan titan = laneTitans.poll();
		int sum=0;
		if(titan.hasReachedTarget()) sum = attack(titan);
		if (!titan.isDefeated()) laneTitans.add(titan);
		return sum;
	}

}
