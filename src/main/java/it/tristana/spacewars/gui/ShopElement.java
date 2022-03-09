package it.tristana.spacewars.gui;

import java.util.List;

import org.bukkit.entity.Player;

import it.tristana.commons.gui.BasicElement;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.config.SettingsShop;

public abstract class ShopElement extends BasicElement {

	protected final SettingsShop settings;
	protected final ArenasManager<SpaceArena> arenasManager;
	
	public ShopElement(SettingsShop settings, ArenasManager<SpaceArena> arenasManager, String name, List<String> lore) {
		super(name, lore);
		this.settings = settings;
		this.arenasManager = arenasManager;
	}

	@Override
	public final boolean closesInventory(Player player) {
		return false;
	}
}
