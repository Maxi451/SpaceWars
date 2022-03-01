package it.tristana.spacewars.arena.player;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import it.tristana.commons.arena.player.BasicArenaPlayer;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;

public class SpacePlayer extends BasicArenaPlayer<SpaceTeam, SpaceArena> implements Tickable {

	private static final int TICKS_FOR_FUEL = 8;
	private static final int TICKS_FOR_RESPAWN = 5;
	private static final int STARTING_LIVES = 5;
	
	private SpaceUser user;
	private Kit kit;
	
	private SpacePlayer lastAttacker;
	
	private int ticksForFuel;
	private int ticksToRespawn;
	private int lives;
	
	public SpacePlayer(SpaceArena arena, SpaceUser user) {
		super(arena, user.getPlayer());
		this.user = user;
		lives = STARTING_LIVES;
	}

	@Override
	public void runTick() {
		if (-- ticksToRespawn > 0) {
			return;
		} else if (ticksToRespawn == 0) {
			respawn();
			return;
		}
		checkFuel();
	}
	
	public void onDeath() {
		lives --;
		SpaceArena.heal(player);
		kit.getGun().resetFmjAndLongBarrel();
		if (lives > 0) {
			ticksToRespawn = TICKS_FOR_RESPAWN + 1;
			player.setGameMode(GameMode.SPECTATOR);
			player.getInventory().clear();
		}
	}
	
	public void giveDefaultItems() {
		PlayerInventory inventory = player.getInventory();
		inventory.setArmorContents(team.getArmor());
		kit.giveItems(inventory);
	}
	
	public int getLives() {
		return lives;
	}
	
	public void onLife() {
		lives ++;
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
	
	public void setLastAttacker(SpacePlayer lastAttacker) {
		this.lastAttacker = lastAttacker;
	}
	
	public SpacePlayer getLastAttacker() {
		return lastAttacker;
	}
	
	public void setPlayingGameMode() {
		player.setGameMode(GameMode.ADVENTURE);
	}
	
	public void upgradePickaxe() {
		kit.upgradePickaxe();
	}
	
	private void respawn() {
		setPlayingGameMode();
		giveDefaultItems();
	}
	
	private void checkFuel() {
		if (hasFuel()) {
			onRefuel();
		} else if (-- ticksForFuel == 0) {
			giveFuel();
		}
		user.getPlayer().setExp((float) ticksForFuel / TICKS_FOR_FUEL);
	}
	
	private boolean hasFuel() {
		Inventory inventory = player.getInventory();
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
		player.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET));
		onRefuel();
	}
	
	private void onRefuel() {
		ticksForFuel = TICKS_FOR_FUEL;
	}
}
