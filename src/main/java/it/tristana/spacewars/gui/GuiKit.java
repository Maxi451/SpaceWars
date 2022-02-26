package it.tristana.spacewars.gui;

import it.tristana.commons.gui.BasicGui;
import it.tristana.commons.interfaces.gui.Element;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.player.kit.ManagerKits;

public class GuiKit extends BasicGui {

	private ManagerKits kitsManager;
	
	public GuiKit(String name, ManagerKits kitsManager) {
		super(name);
		this.kitsManager = kitsManager;
	}

	@Override
	protected Element[] createElements() {
		Kit[] kits = kitsManager.getKitsView();
		Element[] elements = new Element[kits.length];
		for (int i = 0; i < elements.length; i ++) {
			elements[i] = new ElementKit(kits[i]);
		}
		return elements;
	}
}
