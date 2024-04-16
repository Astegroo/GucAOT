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

	//modified this
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans){
		if(laneTitans.isEmpty()){
			return 0;
		}
		int totalResources=0;
		PriorityQueue<Titan> temp=new PriorityQueue<>();
		while(!laneTitans.isEmpty()){
			Titan t=laneTitans.poll();
			if(t.getDistance()<=this.maxRange && t.getDistance()>=this.minRange){
				int resources=attack(t);
				totalResources+=resources;
				if(resources==0){
					temp.add(t);
				}
			}
			else{
				temp.add(t);
			}
		}
		laneTitans.addAll(temp);
		return totalResources;
	}


}
//if (laneTitans.isEmpty())
//		return 0;
//		int resourcesum = 0;
//		PriorityQueue<Titan> temp = new PriorityQueue<>();
//		while (!laneTitans.isEmpty()) {
//		Titan x = laneTitans.remove();
//		if (x.getDistance() >= minRange && x.getDistance() <= maxRange) {
//		int y = attack(x);
//		resourcesum += y;
//		if (y == 0) {
//		temp.add(x);
//		}
//		} else {
//		temp.add(x);
//		}
//		}
//		laneTitans.addAll(temp);
//		return resourcesum;