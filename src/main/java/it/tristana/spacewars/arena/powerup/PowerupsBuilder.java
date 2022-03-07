package it.tristana.spacewars.arena.powerup;

import it.tristana.commons.interfaces.util.Powerup;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupsBuilder {
	
	private PowerupsBuilder() {}
	
	public static Powerup<SpacePlayer>[] createPowerups(SettingsPowerups settings) {
		return new SpacePowerup[] {
			new Powerup1Up(settings),
			new PowerupLongBarrel(settings),
			new PowerupMedicKit(settings),
			new PowerupFmj(settings),
			new PowerupMoney(settings),
			new PowerupFuel(settings),
			new PowerupBetterPickaxes(settings)
		};
	}
}
