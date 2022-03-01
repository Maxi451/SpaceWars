package it.tristana.spacewars.arena.powerup;

import it.tristana.commons.arena.powerup.BasicPowerup;
import it.tristana.spacewars.arena.player.SpacePlayer;

abstract class SpacePowerup extends BasicPowerup<SpacePlayer> {

	SpacePowerup(String name, int spawnChance) {
		super(name, spawnChance);
	}
}
