package it.tristana.spacewars.gui.shop;

import org.bukkit.entity.Player;

import it.tristana.commons.gui.BasicGui;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.gui.Element;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsShop;

public class GuiShop extends BasicGui {

	private final SettingsShop settings;
	private final ArenasManager<SpaceArena, SpacePlayer> arenasManager;
	
	public GuiShop(String name, SettingsShop settings, ArenasManager<SpaceArena, SpacePlayer> arenasManager) {
		super(name);
		this.settings = settings;
		this.arenasManager = arenasManager;
	}

	@Override
	public Element[] getElements(Player player) {
		return new Element[] {
				new ShopElementArmor(settings, arenasManager)
		};
	}
}
