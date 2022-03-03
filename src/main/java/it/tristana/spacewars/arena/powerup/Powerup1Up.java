package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class Powerup1Up extends SpacePowerup {

	public Powerup1Up(SettingsPowerups settings) {
		super(settings.get_1upName(), settings.get_1upChance());
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		return false;
	}
}
