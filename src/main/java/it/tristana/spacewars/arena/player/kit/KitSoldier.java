package it.tristana.spacewars.arena.player.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.gun.GunSoldier;
import it.tristana.spacewars.config.SettingsKits;

public class KitSoldier extends Kit {

	public KitSoldier(SettingsKits settings) {
		super(settings);
	}

	@Override
	public ItemStack getRawDisplayItem() {
		return new ItemStack(Material.IRON_CHESTPLATE);
	}

	@Override
	public String getName() {
		return settings.getSoldierName();
	}

	@Override
	public List<String> getLore() {
		return settings.getSoldierLore();
	}

	@Override
	public double getBaseArmor() {
		return settings.getSoldierArmor();
	}

	@Override
	protected Gun forgeGun() {
		return new GunSoldier(settings);
	}
}
