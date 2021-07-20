package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class UpgradeClassEffectDefender extends UpgradeClassEffect {

	public UpgradeClassEffectDefender() {
		super(new ItemStack(Material.SHIELD), combactClassesValues.getDefenderClassEffect(), PotionEffectType.INCREASE_DAMAGE, true);
	}
}
