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
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		if(laneTitans.isEmpty()){
			return 0;
		}
		Titan t= laneTitans.poll();
		if(t.getDistance()==0) {
			int resources = t.takeDamage(this.getDamage());
			if(resources==0){
				laneTitans.add(t);
			}
			return resources;
		}
		laneTitans.add(t);
		return 0;
	}

}
//if (laneTitans.isEmpty())
//		return 0;
//		Titan x = laneTitans.remove();
//		int y = 0;
//		if(x.getDistance()<=0){
//		y = attack(x);
//		if(y == 0){ //only return him into the queue if he's still alive
//		laneTitans.add(x);
//		}
//		}
//		return y;