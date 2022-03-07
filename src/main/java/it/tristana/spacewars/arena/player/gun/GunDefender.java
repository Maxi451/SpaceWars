package it.tristana.spacewars.arena.player.gun;

import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.kit.KitDefender;
import it.tristana.spacewars.config.SettingsKits;

public class GunDefender extends Gun {

	public GunDefender(SettingsKits settings) {
		super(settings);
	}
	
	@Override
	public Sound getSound() {
		return Sound.BLOCK_CHAIN_HIT;
	}
	
	@Override
	public double getDamage(SpacePlayer player) {
		double damage = super.getDamage(player);
		if (KitDefender.isNearNexus(player, settings)) {
			damage *= (1 + settings.getDefenderBonusDamagePercentage());
		}
		return damage;
	}

	@Override
	protected ItemStack forgeItem() {
		return getDefaultItem();
	}

	@Override
	protected long getBaseReloadTime() {
		return settings.getDefenderGunReload();
	}

	@Override
	protected double getBaseDamage() {
		return settings.getDefenderGunDamage();
	}

	@Override
	protected boolean isBaseFmj() {
		return settings.isDefenderGunFmj();
	}

	@Override
	protected boolean isBaseLongBarrel() {
		return settings.isDefenderGunLongBarrel();
	}
	
	@Override
	protected long getActualReloadTime(SpacePlayer player) {
		long millis = super.getActualReloadTime(player);
		if (KitDefender.isNearNexus(player, settings)) {
			millis = (long) ((1 - settings.getDefenderBonusReloadPercentage()) * millis);
		}
		return millis;
	}
}
