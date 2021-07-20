package it.tristana.spacewars.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryView;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.config.ArenaValues;

public class SpaceListener implements Listener {

	private Main plugin;
	private ArenaValues arenaValues;
	
	public SpaceListener(Main plugin) {
		this.plugin = plugin;
		this.arenaValues = plugin.getArenaValues();
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		SpaceArena arena = getArena(player);
		if (arena != null && arena.hasPlayer(player)) {
			Action action = event.getAction();
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(arena.onRightClick(player));
			}
		}
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Player) {
			SpaceArena arena = plugin.getArenaByWorld(entity.getWorld());
			if (arena != null) {
				Player player = (Player) entity;
				if (arena.isGameRunning() && arena.hasPlayer(player)) {
					if (player.getHealth() - event.getFinalDamage() <= 0) {
						arena.onPlayerDeath(player);
						event.setCancelled(true);
					}
				}
				else {
					event.setCancelled(true);
				}
			}
			else {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInventoryAction(InventoryClickEvent event) {
		HumanEntity human = event.getWhoClicked();
		if (human instanceof Player) {
			Player player = (Player) human;
			SpaceArena arena = getArena(player);
			if (arena != null) {
				if (event.getSlotType() == SlotType.ARMOR) {
					event.setCancelled(true);
				}
				else {
					SpacePlayer spacePlayer = arena.getArenaPlayer(player);
					if (spacePlayer != null) {
						InventoryView view = event.getView();
						if (view != null) {
							String title = view.getTitle();
							boolean cancel = true;
							if (title.equals(arenaValues.getKitChooseGuiName()) && event.getClickedInventory() == view.getTopInventory()) {
								arena.onKitChosen(spacePlayer, event.getSlot());
								player.closeInventory();
							}
							else {
								cancel = false;
							}
							if (cancel) {
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
		else {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerHunger(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	
	private SpaceArena getArena(Player player) {
		return plugin.getArenaByWorld(player.getWorld());
	}
}