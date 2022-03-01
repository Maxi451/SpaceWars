package it.tristana.spacewars.arena.powerup;

import it.tristana.spacewars.arena.player.SpacePlayer;

public class Powerup1Up extends SpacePowerup {

	public Powerup1Up(String name, int spawnChance) {
		super(name, spawnChance);
	}

	@Override
	public boolean doAction(SpacePlayer arenaPlayer) {
		return false;
	}
}
