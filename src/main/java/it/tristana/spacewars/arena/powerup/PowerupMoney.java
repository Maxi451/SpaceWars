package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupMoney extends SpacePowerup {

	PowerupMoney(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.giveMoney(settings.getMoneyAmount());
		return true;
	}

	@Override
	public String getName() {
		return settings.getMoneyName();
	}

	@Override
	public int getSpawnChance() {
		return settings.getMoneyChance();
	}
}
