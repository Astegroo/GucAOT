package game.engine.weapons.factory;

import java.io.IOException;
import java.util.HashMap;

import game.engine.Battle;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;

public class WeaponFactory
{
	private final HashMap<Integer, WeaponRegistry> weaponShop;

	public WeaponFactory() throws IOException
	{
		super();
		weaponShop = DataLoader.readWeaponRegistry();
	}

	public HashMap<Integer, WeaponRegistry> getWeaponShop()
	{
		return weaponShop;
	}

	public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException {
		WeaponRegistry x = weaponShop.get(weaponCode);
		if (x.getPrice() > resources) {
			throw new InsufficientResourcesException(resources);
		} else {
			int remainingResources = resources - x.getPrice();
			return new FactoryResponse(x.buildWeapon(), remainingResources);
		}
	// I have never felt this smart in my life :D, this took like 40 minutes to find out how to do it without hardcoding it.
		  //so technically I first get x which is a WeaponRegistry I got from the hash table using the code.
		  // then I use that registry to check if its price would be higher than the available resources.
		  // if its fine return a factory response that takes a weapon that I made from the method BuildWeapon.
		  // and also take the remaining resources
	}

	public void addWeaponToShop(int code, int price){
		WeaponRegistry a = new WeaponRegistry(code, price);
		weaponShop.put(code, a);
	}

	public void addWeaponToShop(int code, int price, int damage, String name){
		WeaponRegistry a = new WeaponRegistry(code, price, damage, name);
		weaponShop.put(code, a);
	}

	public  void addWeaponToShop(int code, int price, int damage, String name, int minRange, int maxRange){
		WeaponRegistry a = new WeaponRegistry(code, price, damage, name, minRange, maxRange);
		weaponShop.put(code, a);
	}
}
//