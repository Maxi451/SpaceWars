package it.tristana.spacewars.arena.player.gun;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.interfaces.Reloadable;
import it.tristana.spacewars.config.SettingsKits;

public abstract class Gun implements Reloadable {

	protected SettingsKits settings;
	private ItemStack item;
	
	private long reloadTime;
	private double damage;
	
	private long lastShotTime;
	private boolean isFmj;
	private boolean isLongBarrel;
	
	public Gun(SettingsKits settings) {
		this.settings = settings;
		reload();
	}
	
	@Override
	public final void reload() {
		reloadTime = getBaseReloadTime();
		damage = getBaseDamage();
		resetFmjAndLongBarrel();
	}
	
	public final ItemStack getItem() {
		if (item == null) {
			item = forgeItem();
		}
		return item.clone();
	}
	
	public final boolean tryToShoot() {
		long millis = System.currentTimeMillis();
		boolean result = millis >= lastShotTime + reloadTime;
		if (result) {
			lastShotTime = millis;
		}
		return result;
	}
	
	public final double getDamage() {
		return damage;
	}
	
	public final long getReloadTime() {
		return reloadTime;
	}
	
	public final void resetFmjAndLongBarrel() {
		isFmj = isBaseFmj();
		isLongBarrel = isBaseLongBarrel();
	}
	
	public final boolean isFmj() {
		return isFmj;
	}
	
	public final void onFmj() {
		isFmj = true;
	}
	
	public final boolean isLongBarrel() {
		return isLongBarrel;
	}
	
	public final void onLongBarrel() {
		isLongBarrel = true;
	}
	
	public final boolean isThisItem(ItemStack other) {
		return getItem().isSimilar(other);
	}
	
	protected static ItemStack getDefaultItem() {
		return new ItemStack(Material.NETHERITE_HOE);
	}
	
	public abstract Sound getSound();
	
	protected abstract ItemStack forgeItem();
	
	protected abstract long getBaseReloadTime();
	
	protected abstract double getBaseDamage();
	
	protected abstract boolean isBaseFmj();
	
	protected abstract boolean isBaseLongBarrel();
}
