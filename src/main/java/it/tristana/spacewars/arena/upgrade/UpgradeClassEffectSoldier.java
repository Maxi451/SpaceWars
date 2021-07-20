package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class UpgradeClassEffectSoldier extends UpgradeClassEffect {

	public UpgradeClassEffectSoldier() {
		super(new ItemStack(Material.SNOWBALL), combactClassesValues.getSoldierClassEffect(), PotionEffectType.DAMAGE_RESISTANCE, true);
	}
}
