package it.tristana.spacewars.arena;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import it.tristana.commons.arena.player.BasicArenaPlayer;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.arena.kit.CombactClass;
import it.tristana.spacewars.database.SpaceUser;

public class SpacePlayer extends BasicArenaPlayer<SpaceTeam, SpaceArena> implements Tickable {

	private static final int TICKS_TO_FUEL = 8;
	
	private SpaceUser user;
	private CombactClass kit;
	
	private boolean hasFmj;
	private int money;
	private boolean isSoulFireUpgrade;
	private boolean isSniperUpgrade;
	private boolean isDodgeUpgrade;
	private double baseDamage;
	private double baseResistance;
	private long millisToShootAgain;
	private int lastTimeTicksToRespawn;
	private int ticksToRespawn;
	private int ticksToFuel;
	
	public SpacePlayer(SpaceArena arena, Player player, SpaceUser user) {
		super(arena, player);
		this.user = user;
		lastTimeTicksToRespawn = 6;
		ticksToFuel = TICKS_TO_FUEL;
		baseDamage = 1;
	}
	
	public void setKit(CombactClass kit) {
		this.kit = kit;
	}
	
	public CombactClass getKit() {
		return kit;
	}
	
	public boolean hasFmj() {
		return hasFmj;
	}
	
	public void setFmj(boolean hasFmj) {
		this.hasFmj = hasFmj;
	}
	
	public boolean canBeHit() {
		return true;
	}
	
	public SpaceUser getUser() {
		return user;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void addMoney(int money) {
		this.money += money;
	}
	
	public void pay(int money) {
		addMoney(-money);
	}
	
	public void setSoulFireUpgrade(boolean isSoulFireUpgrade) {
		this.isSoulFireUpgrade = isSoulFireUpgrade;
	}
	
	public boolean isSoulFireUpgrade() {
		return isSoulFireUpgrade;
	}
	
	public void setSniperUpgrade(boolean isSniperUpgrade) {
		this.isSniperUpgrade = isSniperUpgrade;
	}
	
	public boolean isSniperUpgrade() {
		return isSniperUpgrade;
	}
	
	public void setDodgeUpgrade(boolean isDodgeUpgrade) {
		this.isDodgeUpgrade = isDodgeUpgrade;
	}
	
	public boolean isDodgeUpgrade() {
		return isDodgeUpgrade;
	}

	public double getBaseDamage() {
		return baseDamage * kit.getGun().getBaseDamage();
	}

	public void setBaseDamage(double baseDamage) {
		this.baseDamage = baseDamage;
	}

	public double getBaseResistance() {
		return baseResistance;
	}

	public void setBaseResistance(double baseResistance) {
		this.baseResistance = baseResistance;
	}

	public boolean canShoot() {
		return System.currentTimeMillis() >= millisToShootAgain;
	}
	
	public void onShoot() {
		millisToShootAgain = System.currentTimeMillis() + (long) (1000 / kit.getGun().getFireRatio());
	}
	
	public long getMillisToShootAgain() {
		return millisToShootAgain;
	}

	public void setMillisToShootAgain(long millisToShootAgain) {
		this.millisToShootAgain = millisToShootAgain;
	}
	
	public boolean isNearOwnNexus() {
		Nexus nexus = team.getNexus();
		boolean result = !nexus.isBroken();
		if (result) {
			result = player.getLocation().distance(nexus.getLocation()) < 32;
		}
		return result;
	}
	
	public void onDeath() {
		ticksToRespawn = lastTimeTicksToRespawn;
		lastTimeTicksToRespawn += 2;
	}
	
	@Override
	public void runTick() {
		ticksToRespawn --;
		if (ticksToRespawn > 0) {
			player.sendTitle("Sei morto", "Respawn in " + ticksToRespawn, 20, 50, 10);
		}
		else if (ticksToRespawn == 0) {
			player.setGameMode(GameMode.ADVENTURE);
			player.teleport(team.getSpawnpoint());
		}
		else {
			ItemStack fuel = arena.getFuel();
			PlayerInventory inventory = player.getInventory();
			if (!inventory.contains(fuel)) {
				ticksToFuel --;
				if (ticksToFuel == 0) {
					inventory.addItem(fuel);
					ticksToFuel = TICKS_TO_FUEL;
				}
			}
			else {
				ticksToFuel = TICKS_TO_FUEL;
			}
		}
	}
}
