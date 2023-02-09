package it.tristana.spacewars.gui.shop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsShop;

public class ShopElementArmor extends ShopElement {

	public ShopElementArmor(SettingsShop settings) {
		super(settings.getArmorName(), settings.getArmorLore());
	}

	@Override
	public double getPrice() {
		return settingsShop.getArmorPrice();
	}

	@Override
	protected void run(SpacePlayer spacePlayer) {
		spacePlayer.addBonusArmor(settingsShop.getArmorBonusArmorPercentage(), true);
	}

	@Override
	protected int getMaxLevel() {
		return settingsShop.getArmorMaxLevel();
	}

	@Override
	protected ItemStack getRawDisplayItem(Player player) {
		return new ItemStack(settingsShop.getArmorIcon());
	}
}