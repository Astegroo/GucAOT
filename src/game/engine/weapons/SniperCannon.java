package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class SniperCannon extends Weapon
{
	public static final int WEAPON_CODE = 2;

	public SniperCannon(int baseDamage)
	{
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans)
	{
		if(laneTitans.isEmpty()){
			return 0;
		}
		Titan t=laneTitans.poll();
		int resources=t.takeDamage(this.getDamage());
		if(resources==0){
			laneTitans.add(t);
		}
		return resources;
	}

}
