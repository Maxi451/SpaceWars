package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeClassEffectSniper extends UpgradeClassEffect {

	public UpgradeClassEffectSniper() {
		super(new ItemStack(Material.BOW), combactClassesValues.getSniperClassEffect(), null, false);
	}

	@Override
	protected void doSomethingElse(final SpacePlayer arenaPlayer) {
		arenaPlayer.setSniperUpgrade(true);
	}
}
