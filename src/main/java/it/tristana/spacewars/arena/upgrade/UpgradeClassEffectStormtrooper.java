package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class UpgradeClassEffectStormtrooper extends UpgradeClassEffect {

	public UpgradeClassEffectStormtrooper() {
		super(new ItemStack(Material.ENDER_EYE), combactClassesValues.getStormtrooperClassEffect(), PotionEffectType.REGENERATION, true);
	}
}
