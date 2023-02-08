package it.tristana.spacewars.arena.player.gun;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.config.SettingsKits;

public class GunSniper extends Gun {

	public GunSniper(SettingsKits settings) {
		super(settings);
	}

	@Override
	public Sound getSound() {
		return Sound.BLOCK_CHAIN_HIT;
	}
	
	@Override
	public double getTargetArmorReducedPercentage() {
		return settings.getSniperEnemyBonusArmorIgnoredPercentage();
	}

	@Override
	protected ItemStack forgeItem() {
		return new ItemStack(Material.DIAMOND_HOE);
	}

	@Override
	protected long getBaseReloadTime() {
		return settings.getSniperGunReload();
	}

	@Override
	protected double getBaseDamage() {
		return settings.getSniperGunDamage();
	}

	@Override
	protected boolean isBaseFmj() {
		return settings.isSniperGunFmj();
	}

	@Override
	protected boolean isBaseLongBarrel() {
		return settings.isSniperGunLongBarrel();
	}
}
