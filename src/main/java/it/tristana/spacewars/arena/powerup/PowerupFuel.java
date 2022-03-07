package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupFuel extends SpacePowerup {

	PowerupFuel(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.giveFuel(settings.getFuelAmount());
		return true;
	}

	@Override
	public String getName() {
		return settings.getFuelName();
	}

	@Override
	public int getSpawnChance() {
		return settings.getFuelChance();
	}
}
