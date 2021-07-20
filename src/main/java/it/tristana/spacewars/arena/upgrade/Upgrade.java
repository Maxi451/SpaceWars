package it.tristana.spacewars.arena.upgrade;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.helper.Utility;
import it.tristana.spacewars.config.CombactClassesValues;
import it.tristana.spacewars.config.UpgradeValues;

public abstract class Upgrade {
	
	protected static CombactClassesValues combactClassesValues;
	protected static UpgradeValues upgradeValues;
	
	public static final double UPGRADING_COST_MULTIPLIER = 1.25;
	protected ItemStack displayItem;
	protected final String name;
	protected BuyResult cantUpgradeReason;
	protected final int maxLevel;
	protected int currentLevel;
	protected double currentCost;
	protected final boolean isTeamUpgrade;
	
	public Upgrade(final ItemStack displayItem, final String name, final int maxLevel, final int currentCost, final boolean isTeamUpgrade) {
		this.name = name;
		this.maxLevel = maxLevel;
		this.currentCost = currentCost;
		this.currentLevel = 0;
		this.isTeamUpgrade = isTeamUpgrade;
		this.displayItem = editItem(displayItem);
		this.cantUpgradeReason = BuyResult.CANT_UPGRADE;
	}

	public static void setup(CombactClassesValues combactClasses, UpgradeValues upgrades) {
		combactClassesValues = combactClasses;
		upgradeValues = upgrades;
		UpgradeDamageBase.reset();
		UpgradeDefenseBase.reset();
		UpgradeFireRatio.reset();
		UpgradeFireworks.reset();
		UpgradeHealth.reset();
		//UpgradeRefillObsidian.reset() does not exists
	}
	
	private final ItemStack editItem(final ItemStack displayItem) {
		ItemMeta itemMeta = displayItem.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.setLore(updateLore());
		displayItem.setItemMeta(itemMeta);
		return displayItem;
	}
	
	private List<String> updateLore() {
		List<String> lines = new ArrayList<String>();
		// TODO unicode
		lines.add(CommonsHelper.replaceAll(upgradeValues.getUpgradeLevel(), new String[] {"{current level}", "{max level}"}, new String[] {currentLevel + "", (maxLevel != -1 ? maxLevel + "" : "∞")}));
		if (checkLevel()) {
			lines.add(CommonsHelper.replaceAll(upgradeValues.getUpgradeCost(), new String[] {"{current cost}", "{coin symbol}"}, new String[] {(int)currentCost + "", currentCost > 1000 ? " ⛃" : " ⛂"}));
		}
		if (isTeamUpgrade) {
			lines.addAll(upgradeValues.getUpgradeAllTeam());
		}
		return lines;
	}
	
	public void upgrade() {
		currentLevel ++;
		currentCost *= UPGRADING_COST_MULTIPLIER;
		displayItem = editItem(displayItem);
	}
	
	public final BuyResult buy(final SpacePlayer arenaPlayer) {
		BuyResult buyResult;
		if (checkLevel()) {
			if (checkUpgradeConditions(arenaPlayer)) {
				if (arenaPlayer.getMoney() >= currentCost) {
					arenaPlayer.pay((int) currentCost);
					buyResult = BuyResult.SUCCESS;
					doAction(arenaPlayer);
					upgrade();
				}
				else {
					buyResult = cantUpgradeReason;
				}
			}
			else {
				buyResult = BuyResult.NOT_ENAUGH_MONEY;
			}
		}
		else {
			buyResult = BuyResult.ALREADY_MAX_LEVEL;
		}
		return buyResult;
	}
	
	public final ItemStack getDisplayItem() {
		return displayItem;
	}
	
	public final String getName() {
		return name;
	}
	
	public final int getMaxLevel() {
		return maxLevel;
	}
	
	public final int getCurrentLevel() {
		return currentLevel;
	}
	
	public final int getCurrentCost() {
		return (int) currentCost;
	}
	
	private boolean checkLevel() {
		return currentLevel < maxLevel || maxLevel == -1;
	}
	
	protected boolean checkUpgradeConditions(final SpacePlayer arenaPlayer) {
		return true;
	}
	
	protected abstract void doAction(final SpacePlayer arenaPlayer);
	
	public enum BuyResult {
		SUCCESS,
		ALREADY_MAX_LEVEL,
		NOT_ENAUGH_MONEY,
		CANT_UPGRADE,
		NEXUS_DESTROYED
	}
	
	protected static int parseIntOrGetDefault(String line, int defaultValue) {
		return Utility.parseIntOrGetDefault(line, defaultValue);
	}
	
	protected static double parseDoubleOrGetDefault(String line, double defaultValue) {
		return Utility.parseDoubleOrGetDefault(line, defaultValue);
	}
}
