package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeClassEffectDestroyer extends UpgradeClassEffect {

	public UpgradeClassEffectDestroyer() {
		super(new ItemStack(Material.TNT), combactClassesValues.getDestroyerClassEffect(), null, false);
	}

	@Override
	protected void doSomethingElse(final SpacePlayer arenaPlayer) {
		ItemStack missiles = new ItemStack(Main.getInstance().getItems().getMissile());
		missiles.setAmount(4);
		arenaPlayer.getPlayer().getInventory().addItem(missiles);
	}
}