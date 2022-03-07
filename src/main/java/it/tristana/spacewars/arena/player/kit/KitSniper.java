package it.tristana.spacewars.arena.player.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.gun.GunSniper;
import it.tristana.spacewars.config.SettingsKits;

public class KitSniper extends Kit {

	public KitSniper(SettingsKits settings) {
		super(settings);
	}

	@Override
	public ItemStack getRawDisplayItem() {
		return new ItemStack(Material.BOW);
	}

	@Override
	public String getName() {
		return settings.getSniperName();
	}

	@Override
	public List<String> getLore() {
		return settings.getSniperLore();
	}

	@Override
	public double getBaseArmor() {
		return settings.getSniperArmor();
	}

	@Override
	protected Gun forgeGun() {
		return new GunSniper(settings);
	}
}
