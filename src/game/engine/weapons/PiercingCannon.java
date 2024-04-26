package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class PiercingCannon extends Weapon
{
	public static final int WEAPON_CODE = 1;
	public PiercingCannon(int baseDamage)
	{
		super(baseDamage);
	}
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans)
	{
		if (laneTitans.isEmpty()) return 0;
		int sum = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++)
		{
			Titan titan = laneTitans.poll();
			sum += attack(titan);
			if (!titan.isDefeated()) temp.add(titan);
		}
		while(!temp.isEmpty())laneTitans.add(temp.remove());
		return sum;
	}


}