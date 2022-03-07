package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsPowerups;

public class PowerupMedicKit extends SpacePowerup {

	PowerupMedicKit(SettingsPowerups settings) {
		super(settings);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		SpaceArena.heal(arenaPlayer.getPlayer());
		return true;
	}

	@Override
	public String getName() {
		return settings.getMedicKitName();
	}

	@Override
	public int getSpawnChance() {
		return settings.getMedicKitChance();
	}
}
