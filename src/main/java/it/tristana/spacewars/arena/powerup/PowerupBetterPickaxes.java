package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupBetterPickaxes extends SpacePowerup {

	PowerupBetterPickaxes(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.getTeam().getPlayers().forEach(player -> {
			player.upgradePickaxe();
		});
		return true;
	}

	@Override
	public String getName() {
		return settings.getBetterPickaxesName();
	}

	@Override
	public int getSpawnChance() {
		return settings.getBetterPickaxesChance();
	}
}
