package game.engine.weapons.factory;

import java.io.IOException;
import java.util.HashMap;

import game.engine.Battle;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.*;

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