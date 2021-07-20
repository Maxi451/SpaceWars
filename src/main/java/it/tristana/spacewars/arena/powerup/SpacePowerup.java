package it.tristana.spacewars.arena.powerup;

import it.tristana.commons.arena.powerup.BasicPowerup;
import it.tristana.spacewars.arena.SpacePlayer;

public abstract class SpacePowerup extends BasicPowerup<SpacePlayer> {

	public SpacePowerup(String name, int spawnChance) {
		super(name, spawnChance);
	}
}
