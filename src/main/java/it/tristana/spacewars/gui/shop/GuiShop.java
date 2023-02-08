package it.tristana.spacewars.gui.shop;

import org.bukkit.entity.Player;

import it.tristana.commons.gui.BasicGui;
import it.tristana.commons.interfaces.gui.Element;
import it.tristana.spacewars.config.SettingsShop;

public class GuiShop extends BasicGui {

	private final SettingsShop settings;
	
	public GuiShop(String name, SettingsShop settings) {
		super(name);
		this.settings = settings;
	}

	@Override
	public Element[] getElements(Player player) {
		return new Element[] {
				new ShopElementArmor(settings)
		};
	}
}
