package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupLongBarrel extends SpacePowerup {

	public PowerupLongBarrel(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		Gun gun = arenaPlayer.getKit().getGun();
		boolean result = gun.isLongBarrel();
		if (!result) {
			gun.onLongBarrel();
		}
		return result;
	}

	@Override
	public String getName() {
		return settings.getLongBarrelName();
	}

	@Override
	public int getSpawnChance() {
		return settings.getLongBarrelChance();
	}
}
