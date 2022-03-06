package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class Powerup1Up extends SpacePowerup {

	public Powerup1Up(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.onLife();
		return true;
	}

	@Override
	public String getName() {
		return settings.get_1upName();
	}

	@Override
	public int getSpawnChance() {
		return settings.get_1upChance();
	}
}
