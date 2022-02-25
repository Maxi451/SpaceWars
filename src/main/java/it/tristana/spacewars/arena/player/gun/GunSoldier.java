package it.tristana.spacewars.arena.player.gun;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.config.SettingsKits;

public class GunSoldier extends Gun {

	public GunSoldier(SettingsKits settings) {
		super(settings);
	}

	@Override
	public Sound getSound() {
		return Sound.BLOCK_ANVIL_HIT;
	}

	@Override
	protected long getBaseReloadTime() {
		return 1000;
	}

	@Override
	protected double getBaseDamage() {
		return 2;
	}

	@Override
	protected boolean isBaseFmj() {
		return false;
	}

	@Override
	protected boolean isBaseLongBarrel() {
		return false;
	}

	@Override
	protected ItemStack forgeItem() {
		return getDefaultItem();
	}
}
