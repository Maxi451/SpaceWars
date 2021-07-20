package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeRefillObsidian extends Upgrade {

	public UpgradeRefillObsidian() {
		super(new ItemStack(Material.OBSIDIAN), upgradeValues.getUpgradeObsidianName(), parseIntOrGetDefault(upgradeValues.getUpgradeObsidianMaxLevel(), -1), parseIntOrGetDefault(upgradeValues.getUpgradeObsidianCost(), 1000), true);
		this.cantUpgradeReason = BuyResult.NEXUS_DESTROYED;
	}

	@Override
	protected void doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.getTeam().getNexus().build();
	}
	
	@Override
	protected boolean checkUpgradeConditions(SpacePlayer arenaPlayer) {
		return !arenaPlayer.getTeam().getNexus().isBroken();
	}
}
