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
		return settings.getSoldierGunReload();
	}

	@Override
	protected double getBaseDamage() {
		return settings.getSoldierGunDamage();
	}

	@Override
	protected boolean isBaseFmj() {
		return settings.isSoldierGunFmj();
	}

	@Override
	protected boolean isBaseLongBarrel() {
		return settings.isSoldierGunLongBarrel();
	}

	@Override
	protected ItemStack forgeItem() {
		return getDefaultItem();
	}
}
