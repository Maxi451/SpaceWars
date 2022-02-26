package it.tristana.spacewars.arena.player;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.arena.player.BasicArenaPlayer;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;

public class SpacePlayer extends BasicArenaPlayer<SpaceTeam, SpaceArena> implements Tickable {

	private static final int TICKS_FOR_FUEL = 8;
	
	private SpaceUser user;
	private Kit kit;
	
	private int ticksForFuel;
	
	public SpacePlayer(SpaceArena arena, SpaceUser user) {
		super(arena, user.getPlayer());
		this.user = user;
	}

	@Override
	public void runTick() {
		if (hasFuel()) {
			onRefuel();
		} else if (-- ticksForFuel == 0) {
			giveFuel();
		}
		user.getPlayer().setExp((float) ticksForFuel / TICKS_FOR_FUEL);
	}
	
	public SpaceUser getUser() {
		return user;
	}
	
	public void setKit(Kit kit) {
		this.kit = kit;
	}
	
	public Kit getKit() {
		return kit;
	}
	
	private boolean hasFuel() {
		Inventory inventory = user.getPlayer().getInventory();
		int size = inventory.getSize();
		for (int i = 0; i < size; i ++) {
			ItemStack item = inventory.getItem(i);
			if (item != null && item.getType() == Material.FIREWORK_ROCKET) {
				return true;
			}
		}
		return false;
	}
	
	private void giveFuel() {
		user.getPlayer().getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET));
		onRefuel();
	}
	
	private void onRefuel() {
		ticksForFuel = TICKS_FOR_FUEL;
	}
}
