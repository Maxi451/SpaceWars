package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeDamageBase extends Upgrade {

	private static double multiplier = 1 + parseDoubleOrGetDefault(upgradeValues.getUpgradeDamageValue(), 7.5) / 100;
	
	public UpgradeDamageBase() {
		super(new ItemStack(Material.FIRE_CHARGE), upgradeValues.getUpgradeDamageName(), parseIntOrGetDefault(upgradeValues.getUpgradeDamageMaxLevel(), 4), parseIntOrGetDefault(upgradeValues.getUpgradeDamageCost(), 450), false);
	}

	@Override
	protected void doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.setBaseDamage(arenaPlayer.getBaseDamage() * multiplier);
	}
	
	public static void reset() {
		multiplier = 1 + parseDoubleOrGetDefault(upgradeValues.getUpgradeDamageValue(), 7.5) / 100;
	}
}
