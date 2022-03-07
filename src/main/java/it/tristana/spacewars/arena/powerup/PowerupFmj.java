package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupFmj extends SpacePowerup {

	PowerupFmj(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		Gun gun = arenaPlayer.getKit().getGun();
		boolean doesNotHaveFmj = !gun.isFmj();
		if (doesNotHaveFmj) {
			gun.onFmj();
		}
		return doesNotHaveFmj;
	}

	@Override
	public String getName() {
		return settings.getFmjName();
	}

	@Override
	public int getSpawnChance() {
		return settings.getFmjChance();
	}
}
