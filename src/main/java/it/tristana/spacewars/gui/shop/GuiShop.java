package it.tristana.spacewars.gui.shop;

import it.tristana.commons.gui.BasicGui;
import it.tristana.commons.interfaces.gui.Element;
import it.tristana.spacewars.config.SettingsShop;

public class GuiShop extends BasicGui {

	private SettingsShop settings;
	
	public GuiShop(String name, SettingsShop settings) {
		super(name);
		this.settings = settings;
	}

	@Override
	protected Element[] createElements() {
		return new Element[] {
				
		};
	}
}
