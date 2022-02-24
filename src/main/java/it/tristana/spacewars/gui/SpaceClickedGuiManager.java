package it.tristana.spacewars.gui;

import org.bukkit.event.inventory.InventoryEvent;

import it.tristana.commons.gui.BasicClickedGuiManager;

public class SpaceClickedGuiManager extends BasicClickedGuiManager {

	@Override
	protected String getInventoryName(InventoryEvent event) {
		return event.getView().getTitle();
	}
}
