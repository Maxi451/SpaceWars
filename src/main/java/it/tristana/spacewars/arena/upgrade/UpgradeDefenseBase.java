package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeDefenseBase extends Upgrade {

	private static int value = parseIntOrGetDefault(upgradeValues.getUpgradeDefenseValue(), 5);
	
	public UpgradeDefenseBase() {
		super(new ItemStack(Material.DIAMOND_CHESTPLATE), upgradeValues.getUpgradeDefenseName(), parseIntOrGetDefault(upgradeValues.getUpgradeDefenseMaxLevel(), 4), parseIntOrGetDefault(upgradeValues.getUpgradeDefenseCost(), 450), false);
	}

	@Override
	protected void doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.setBaseResistance(arenaPlayer.getBaseResistance() + value);
	}
	
	static void reset() {
		value = parseIntOrGetDefault(upgradeValues.getUpgradeDefenseValue(), 5);
	}
}
