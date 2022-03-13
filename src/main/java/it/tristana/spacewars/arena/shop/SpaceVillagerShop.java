package it.tristana.spacewars.arena.shop;

import org.bukkit.Location;

import it.tristana.commons.arena.BasicVillagerShop;

public class SpaceVillagerShop extends BasicVillagerShop {

	public SpaceVillagerShop(Location location, String name) {
		super(location, name);
	}

	@Override
	public void spawnEntity() {
		super.spawnEntity();
		villager.setVillagerLevel(5);
	}
	
	@Override
	protected void removeAi() {
		villager.setAI(false);
	}

	@Override
	protected void updateLookingDirection() {
		villager.teleport(location);
	}
}
