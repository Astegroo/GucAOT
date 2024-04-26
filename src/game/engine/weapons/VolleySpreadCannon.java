package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon
{
	public static final int WEAPON_CODE = 3;

	private final int minRange;
	private final int maxRange;

	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange)
	{
		super(baseDamage);
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getMinRange()
	{
		return minRange;
	}

	public int getMaxRange()
	{
		return maxRange;
	}
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans)
	{
		if (laneTitans.isEmpty()) return 0;
		int sum = 0;
		PriorityQueue<Titan> temp = new PriorityQueue<>();
		while (!laneTitans.isEmpty())
		{
			Titan titan = laneTitans.poll();
			if (titan.getDistance() >= minRange && titan.getDistance() <= maxRange)
			{
				sum += attack(titan);
				if (!titan.isDefeated()) temp.add(titan);
			}
			else temp.add(titan);

		}
		laneTitans.addAll(temp);
		return sum;
	}


}