package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupLongBarrel extends SpacePowerup {

	public PowerupLongBarrel(SettingsPowerups settings) {
		super(settings.getLongBarrelName(), settings.getLongBarrelChance());
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		return false;
	}
}
