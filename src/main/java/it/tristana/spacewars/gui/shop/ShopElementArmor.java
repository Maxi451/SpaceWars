package it.tristana.spacewars.gui.shop;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsShop;

public class ShopElementArmor extends ShopElement {

	public ShopElementArmor(SettingsShop settings, ArenasManager<SpaceArena, SpacePlayer> arenasManager, String name, List<String> lore) {
		super(settings, arenasManager, name, lore);
	}

	@Override
	public double getPrice() {
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean run(SpacePlayer spacePlayer) {
		return false;
	}

	@Override
	protected ItemStack getRawDisplayItem(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
}
