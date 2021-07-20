package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeHealth extends Upgrade {

	private static int value = parseIntOrGetDefault(upgradeValues.getUpgradeHealthValue(), 4);
	
	public UpgradeHealth() {
		super(new ItemStack(Material.REDSTONE), upgradeValues.getUpgradeHealthName(), parseIntOrGetDefault(upgradeValues.getUpgradeHealthMaxLevel(), 4), parseIntOrGetDefault(upgradeValues.getUpgradeHealthCost(), 450), false);
	}

	@Override
	protected void doAction(SpacePlayer arenaPlayer) {
		SpaceArena.addBaseLife(arenaPlayer.getPlayer(), value);
	}
	
	static void reset() {
		value = parseIntOrGetDefault(upgradeValues.getUpgradeHealthValue(), 4);
	}
}
