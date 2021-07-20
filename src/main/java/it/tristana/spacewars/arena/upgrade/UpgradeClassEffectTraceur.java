package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeClassEffectTraceur extends UpgradeClassEffect {

	public UpgradeClassEffectTraceur() {
		super(new ItemStack(Material.FEATHER), combactClassesValues.getTraceurClassEffect(), null, false);
	}
	
	@Override
	public void doSomethingElse(final SpacePlayer arenaPlayer) {
		arenaPlayer.setDodgeUpgrade(true);
	}
}
