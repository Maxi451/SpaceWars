package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeFireRatio extends Upgrade {

	private static double multiplier = 1 - parseDoubleOrGetDefault(upgradeValues.getUpgradeFireRatioValue(), 7.5) / 100;
	
	public UpgradeFireRatio() {
		super(new ItemStack(Material.DISPENSER), upgradeValues.getUpgradeFireRatioName(), parseIntOrGetDefault(upgradeValues.getUpgradeFireRatioMaxLevel(), 4), parseIntOrGetDefault(upgradeValues.getUpgradeFireRatioCost(), 450), false);
	}
	
	@Override
	protected void doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.setMillisToShootAgain((long) (arenaPlayer.getMillisToShootAgain() * multiplier));
	}
	
	static void reset() {
		multiplier = 1 - parseDoubleOrGetDefault(upgradeValues.getUpgradeFireRatioValue(), 7.5) / 100;
	}
}
