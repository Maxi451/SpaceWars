package it.tristana.spacewars.arena.upgrade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpacePlayer;

public class UpgradeFireworks extends Upgrade {

	private static ItemStack[] items;
	static {
		setItems();
	}
	
	public UpgradeFireworks() {
		super(new ItemStack(Material.FIREWORK_ROCKET), upgradeValues.getUpgradeFuelName(), parseIntOrGetDefault(upgradeValues.getUpgradeFuelMaxLevel(), -1), parseIntOrGetDefault(upgradeValues.getUpgradeFuelCost(), 30), false);
	}

	@Override
	protected void doAction(SpacePlayer arenaPlayer) {
		arenaPlayer.getPlayer().getInventory().addItem(items);
	}
	
	static void reset() {
		setItems();
	}
	
	private static void setItems() {
		int quantity = parseIntOrGetDefault(upgradeValues.getUpgradeFuelValue(), 2);
		items = new ItemStack[quantity];
		for (int i = 0; i < quantity; i ++) {
			items[i] = Main.getInstance().getItems().getFuel();
		}
	}
}
