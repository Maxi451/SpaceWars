package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeClassEffectPyromaniac extends UpgradeClassEffect {

	public UpgradeClassEffectPyromaniac() {
		super(new ItemStack(Material.BLAZE_POWDER), combactClassesValues.getPyromaniacClassEffect(), null, false);
	}
	
	@Override
	protected void doSomethingElse(final SpacePlayer arenaPlayer) {
		arenaPlayer.setSoulFireUpgrade(true);
	}
}
