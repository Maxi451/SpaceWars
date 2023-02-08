package it.tristana.spacewars.gui.shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsShop;

public class ShopElementArmor extends ShopElement {

	public ShopElementArmor(SettingsShop settings, ArenasManager<SpaceArena, SpacePlayer> arenasManager) {
		super(settings, arenasManager, settings::getArmorName, settings::getArmorLore);
	}

	@Override
	public double getPrice() {
		return settings.getArmorPrice();
	}

	@Override
	protected boolean run(SpacePlayer spacePlayer) {
		spacePlayer.addBonusArmor(settings.getArmorBonusArmorPercentage(), true);
		return true;
	}

	@Override
	protected ItemStack getRawDisplayItem(Player player) {
		return new ItemStack(Material.IRON_CHESTPLATE);
	}

	@Override
	protected int getMaxLevel() {
		return settings.getArmorMaxLevel();
	}
}
