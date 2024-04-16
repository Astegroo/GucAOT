package game.engine.interfaces;

public interface Attackee

{
	int getCurrentHealth();
	void setCurrentHealth(int health);
	int getResourcesValue();
	default boolean isDefeated(){
		return this.getCurrentHealth() <= 0;
	}
	default int takeDamage(int damage){
			if (this.getCurrentHealth()-damage <= 0 ){
				this.setCurrentHealth(this.getCurrentHealth()-damage);
				return this.getResourcesValue();
			}
		    else {
				this.setCurrentHealth(this.getCurrentHealth()-damage);
				return 0;
			}

	}
}
//jana was here