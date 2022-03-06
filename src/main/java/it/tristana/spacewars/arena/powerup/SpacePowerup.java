package it.tristana.spacewars.arena.powerup;

import it.tristana.commons.interfaces.util.Powerup;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

abstract class SpacePowerup implements Powerup<SpacePlayer> {

	protected SettingsPowerups settings;
	
	SpacePowerup(SettingsPowerups settings) {
		this.settings = settings;
	}
}
