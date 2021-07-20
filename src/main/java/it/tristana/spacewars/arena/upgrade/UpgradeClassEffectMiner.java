package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class UpgradeClassEffectMiner extends UpgradeClassEffect {

	public UpgradeClassEffectMiner() {
		super(new ItemStack(Material.DIAMOND_PICKAXE), combactClassesValues.getMinerClassEffect(), PotionEffectType.FAST_DIGGING, true);
	}
}
