package it.tristana.spacewars.arena.player.kit;

import java.util.Arrays;
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
		return "Kit miner";
	}

	@Override
	public List<String> getLore() {
		return Arrays.asList("This kit", "is diggy");
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

	@Override
	public double getBaseArmor() {
		return 33;
	}
}
