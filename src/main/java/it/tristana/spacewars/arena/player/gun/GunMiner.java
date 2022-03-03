package it.tristana.spacewars.arena.player.gun;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.config.SettingsKits;

public class GunMiner extends Gun {

	public GunMiner(SettingsKits settings) {
		super(settings);
	}

	@Override
	public ItemStack forgeItem() {
		return getDefaultItem();
	}

	@Override
	public Sound getSound() {
		return Sound.ENTITY_IRON_GOLEM_HURT;
	}

	@Override
	protected long getBaseReloadTime() {
		return settings.getMinerGunReload();
	}

	@Override
	protected double getBaseDamage() {
		return settings.getMinerGunDamage();
	}

	@Override
	protected boolean isBaseFmj() {
		return settings.isMinerGunFmj();
	}

	@Override
	protected boolean isBaseLongBarrel() {
		return settings.isMinerGunLongBarrel();
	}
}
