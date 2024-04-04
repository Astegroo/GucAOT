package game.engine.weapons;

import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;

public class WeaponRegistry
{
	private final int code;
	private int price;
	private int damage;
	private String name;
	private int minRange;
	private int maxRange;

	public WeaponRegistry(int code, int price)
	{
		super();
		this.code = code;
		this.price = price;
	}

	public WeaponRegistry(int code, int price, int damage, String name)
	{
		super();
		this.code = code;
		this.price = price;
		this.damage = damage;
		this.name = name;
	}

	public WeaponRegistry(int code, int price, int damage, String name, int minRange, int maxRange)
	{
		super();
		this.code = code;
		this.price = price;
		this.damage = damage;
		this.name = name;
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public int getCode()
	{
		return code;
	}

	public int getPrice()
	{
		return price;
	}

	public int getDamage()
	{
		return damage;
	}

	public String getName()
	{
		return name;
	}

	public int getMinRange()
	{
		return minRange;
	}

	public int getMaxRange()
	{
		return maxRange;
	}

	public Weapon buildWeapon(){
		if (this.code == 1){
			return new PiercingCannon(damage);
		}
		else if (this.code == 2){
			return new SniperCannon(damage);
		}
		else if (this.code == 3){
			return new VolleySpreadCannon(damage,minRange,maxRange);
		}
		else{
			return new WallTrap(damage);
		}
	}


}
//