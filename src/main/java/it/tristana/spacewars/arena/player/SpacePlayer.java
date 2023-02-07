package it.tristana.spacewars.arena.player;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import it.tristana.commons.arena.player.BasicArenaPlayer;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.commons.interfaces.shop.BalanceHolder;
import it.tristana.spacewars.arena.Shootable;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.kit.Kit;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;

public class SpacePlayer extends BasicArenaPlayer<SpaceTeam, SpaceArena> implements Tickable, Shootable, BalanceHolder {

	private static final int TICKS_FOR_FUEL = 8;
	private static final int TICKS_FOR_RESPAWN = 5;

	private SpaceUser user;
	private Kit kit;
	private double bonusArmor;
	private double bonusDamage;
	private double bonusArmorPercentage;
	private double bonusDamagePercentage;
	private double bonusIgnoredArmor;
	private double bonusIgnoredArmorPercentage;

	private SpacePlayer lastAttacker;

	private double money;
	private int ticksForFuel;
	private int ticksToRespawn;

	public SpacePlayer(SpaceArena arena, SpaceUser user) {
		super(arena, user.getPlayer().getPlayer());
		this.user = user;
		ticksForFuel = TICKS_FOR_FUEL;
	}

	@Override
	public void runTick() {
		giveTickMoney();
		if (-- ticksToRespawn > 0) {
			return;
		}
		if (ticksToRespawn == 0) {
			respawn();
			return;
		}
		checkFuel();
	}

	@Override
	public void giveMoney(double money) {
		this.money += money;
		player.setLevel((int) this.money);
	}

	@Override
	public double getMoney() {
		return money;
	}

	@Override
	public boolean tryToPay(double money) {
		boolean flag = this.money >= money;
		if (flag) {
			giveMoney(-money);
		}
		return flag;
	}

	public void onDeath() {
		team.removeLife();
		lastAttacker = null;
		SpaceArena.heal(player);
		kit.getGun().resetFmjAndLongBarrel();
		if (team.getLives() > 0 && !team.getNexus().isBroken()) {
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

	public void onLife() {
		team.addLife();
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

	public double getTotalArmor() {
		return getTotalArmor(0);
	}

	public double getTotalArmor(double armorIgnoredPercentage) {
		return Math.max((kit.getArmor() + bonusArmor) * (1 + bonusArmorPercentage) * (1 - armorIgnoredPercentage), 0);
	}

	public double getTotalDamage() {
		return (kit.getGun().getDamage() + bonusDamage) * (1 + bonusDamagePercentage);
	}

	public double getBonusIgnoredArmor() {
		return bonusIgnoredArmor;
	}

	public double getBonusIgnoredArmorPercentage() {
		return bonusIgnoredArmorPercentage;
	}

	public void addBonusArmor(double armor, boolean percentage) {
		if (percentage) {
			bonusArmorPercentage += armor;
		} else {
			bonusArmor += armor;
		}
	}

	public void addBonusDamage(double damage, boolean percentage) {
		if (percentage) {
			bonusDamagePercentage += damage;
		} else {
			bonusDamage += damage;
		}
	}

	public void addBonusArmorIgnored(double armorIgnored, boolean percentage) {
		if (percentage) {
			bonusIgnoredArmorPercentage += armorIgnored;
		} else {
			bonusIgnoredArmor += armorIgnored;
		}
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
		int slot = getPickaxeSlot();
		if (slot >= 0) {
			player.getInventory().setItem(slot, kit.getPickaxe());
		}
	}

	public void giveFuel() {
		giveFuel(1);
	}

	public void giveFuel(int amount) {
		player.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET, amount));
		onRefuel();
	}

	private int getPickaxeSlot() {
		ItemStack[] items = player.getInventory().getContents();
		for (int i = 0; i < items.length; i ++) {
			if (items[i] != null && items[i].getType() == Kit.PICKAXE_MATERIAL) {
				return i;
			}
		}
		return -1;
	}

	private void giveTickMoney() {
		giveMoney(Math.pow(arena.getCurrentTick(), 0.25));
	}

	private void respawn() {
		setPlayingGameMode();
		giveDefaultItems();
		player.teleport(team.getSpawnpoint());
	}

	private void checkFuel() {
		if (hasFuel()) {
			onRefuel();
		} else if (-- ticksForFuel <= 0) {
			giveFuel(1);
		}
		user.getPlayer().getPlayer().setExp(1 - (float) ticksForFuel / TICKS_FOR_FUEL);
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

	private void onRefuel() {
		ticksForFuel = TICKS_FOR_FUEL;
	}
}
