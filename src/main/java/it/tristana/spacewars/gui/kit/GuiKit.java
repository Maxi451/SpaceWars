package it.tristana.spacewars.gui.kit;

import org.bukkit.entity.Player;

import it.tristana.commons.gui.BasicGui;
import it.tristana.commons.interfaces.gui.Element;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.player.kit.KitsManager;

public class GuiKit extends BasicGui {

	private KitsManager kitsManager;
	
	public GuiKit(String name, KitsManager kitsManager) {
		super(name);
		this.kitsManager = kitsManager;
	}

	@Override
	protected Element[] getElements(Player player) {
		Kit[] kits = kitsManager.getKitsView();
		Element[] elements = new Element[kits.length];
		for (int i = 0; i < elements.length; i ++) {
			elements[i] = new ElementKit(kits[i]);
		}
		return elements;
	}
}
