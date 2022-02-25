package it.tristana.spacewars.arena.player.kit;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.gun.GunMiner;
import it.tristana.spacewars.config.SettingsKits;

public class KitMiner extends Kit {

	public KitMiner(SettingsKits settings) {
		super(settings);
	}

	@Override
	protected Gun forgeGun() {
		return new GunMiner(settings);
	}
	
	@Override
	protected ItemStack getPickaxe() {
		ItemStack pickaxe = super.getPickaxe();
		pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
		return pickaxe;
	}
}
