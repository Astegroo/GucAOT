package game.engine.weapons;

import game.engine.interfaces.Attackee;
import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class PiercingCannon extends Weapon
{
	public static final int WEAPON_CODE = 1;

	public PiercingCannon(int baseDamage) {
		super(baseDamage);
	}

	//adjusted this
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
		if (laneTitans.isEmpty()){
			return 0;
		}
		int totalResources=0;
		PriorityQueue<Titan> temp=new PriorityQueue<>();
		for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++) {


			Titan t=laneTitans.poll();
			int resources=t.takeDamage(this.getDamage());
			totalResources+=resources;
			if(resources==0){
				temp.add(t);
			}
		}
		laneTitans.addAll(temp);
		return totalResources;
	}
}
