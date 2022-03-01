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
		return 2000;
	}

	@Override
	protected double getBaseDamage() {
		return 3;
	}

	@Override
	protected boolean isBaseFmj() {
		return false;
	}

	@Override
	protected boolean isBaseLongBarrel() {
		return false;
	}
}
