package it.tristana.spacewars.arena.player.kit;

import java.util.List;

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
	public ItemStack getRawDisplayItem() {
		return new ItemStack(PICKAXE_MATERIAL);
	}

	@Override
	public String getName() {
		return settings.getMinerName();
	}

	@Override
	public List<String> getLore() {
		return settings.getMinerLore();
	}

	@Override
	public double getBaseArmor() {
		return settings.getMinerArmor();
	}

	@Override
	protected Gun forgeGun() {
		return new GunMiner(settings);
	}
	
	@Override
	protected ItemStack getRawPickaxe() {
		ItemStack pickaxe = super.getRawPickaxe();
		pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
		return pickaxe;
	}
}
