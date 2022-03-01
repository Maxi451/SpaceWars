package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;

public class PowerupLongBarrel extends SpacePowerup {

	public PowerupLongBarrel(String name, int spawnChance) {
		super(name, spawnChance);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		return false;
	}
}
