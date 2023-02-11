package it.tristana.spacewars.gui.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsShop;

public class ShopElementRepairNexus extends ShopElement {

	public ShopElementRepairNexus(SettingsShop settings) {
		super(settings.getRepairNexusName(), settings.getRepairNexusLore());
	}

	@Override
	public double getPrice() {
		return settingsShop.getRepairNexusPrice();
	}
	
	@Override
	public boolean isTeamUpgrade() {
		return true;
	}

	@Override
	protected void run(SpacePlayer spacePlayer) {
		spacePlayer.getTeam().getNexus().build();
	}

	@Override
	protected int getMaxLevel() {
		return settingsShop.getRepairNexusMaxLevel();
	}

	@Override
	protected ItemStack getRawDisplayItem(Player player) {
		return new ItemStack(settingsShop.getRepairNexusIcon());
	}

	@Override
	public boolean doAction(SpacePlayer balanceHolder) {
		return !balanceHolder.getTeam().getNexus().isBroken();
	}
}
