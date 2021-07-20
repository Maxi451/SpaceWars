package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeClassEffectTank extends UpgradeClassEffect {

	public UpgradeClassEffectTank() {
		super(new ItemStack(Material.MILK_BUCKET), combactClassesValues.getTankClassEffect(), null, false);
	}
	
	@Override
	protected void doSomethingElse(final SpacePlayer arenaPlayer) {
		arenaPlayer.getPlayer().removePotionEffect(PotionEffectType.SLOW);
	}
}
