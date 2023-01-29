package it.tristana.spacewars.gui.kit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.gui.BasicElement;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.event.SpaceKitChosenEvent;

public class ElementKit extends BasicElement {

	private Kit kit;
	
	public ElementKit(Kit kit) {
		super(kit.getName(), kit.getLore());
		this.kit = kit;
	}

	@Override
	public void onClick(Player player) {
		Bukkit.getPluginManager().callEvent(new SpaceKitChosenEvent(player, kit));
	}

	@Override
	protected ItemStack getRawDisplayItem(Player player) {
		return kit.getRawDisplayItem();
	}

	@Override
	public boolean closesInventory(Player player) {
		return true;
	}
}
