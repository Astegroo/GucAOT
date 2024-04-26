package game.engine.titans;

public class ArmoredTitan extends Titan {
	public static final int TITAN_CODE = 3;

	public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
						int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}

	@Override
	public int takeDamage(int damage) {
		if (this.getCurrentHealth() - (damage / 4) <= 0) {
			this.setCurrentHealth(this.getCurrentHealth() - (damage / 4));
			return this.getResourcesValue();
		} else {
			this.setCurrentHealth(this.getCurrentHealth() - (damage / 4));
			return 0;
		}
	}
}
