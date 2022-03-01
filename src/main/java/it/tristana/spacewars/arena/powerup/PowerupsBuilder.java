package it.tristana.spacewars.arena.powerup;

import it.tristana.commons.interfaces.util.Powerup;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupsBuilder {
	
	private SettingsPowerups settings;
	
	public PowerupsBuilder(SettingsPowerups settings) {
		this.settings = settings;
	}
	
	public Powerup<SpacePlayer>[] createPowerups() {
		return new SpacePowerup[] {
			new Powerup1Up(settings.get_1upName(), settings.get_1upChance()),
			new PowerupLongBarrel(settings.getLongBarrelName(), settings.getLongBarrelChance())
		};
	}
}
