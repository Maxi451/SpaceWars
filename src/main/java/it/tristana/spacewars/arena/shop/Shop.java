package it.tristana.spacewars.arena.shop;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.upgrade.Upgrade;
import it.tristana.spacewars.arena.upgrade.UpgradeClassEffect;
import it.tristana.spacewars.arena.upgrade.UpgradeDamageBase;
import it.tristana.spacewars.arena.upgrade.UpgradeDefenseBase;
import it.tristana.spacewars.arena.upgrade.UpgradeFireRatio;
import it.tristana.spacewars.arena.upgrade.UpgradeFireworks;
import it.tristana.spacewars.arena.upgrade.UpgradeHealth;
import it.tristana.spacewars.arena.upgrade.UpgradeRefillObsidian;

public abstract class Shop {
	
	private Upgrade[] upgrades;
	protected final UpgradeClassEffect upgradeClassEffect;
	private final UpgradeDamageBase upgradeDamageBase;
	private final UpgradeDefenseBase upgradeDefenseBase;
	private final UpgradeFireRatio upgradeFireRatio;
	private final UpgradeFireworks upgradeFireworks;
	private final UpgradeHealth upgradeHealth;
	private final UpgradeRefillObsidian upgradeRefillObsidian;
	
	public Shop(UpgradeClassEffect classEffect) {
		this.upgradeClassEffect = classEffect;
		upgradeDamageBase = new UpgradeDamageBase();
		upgradeDefenseBase = new UpgradeDefenseBase();
		upgradeFireRatio = new UpgradeFireRatio();
		upgradeFireworks = new UpgradeFireworks();
		upgradeHealth = new UpgradeHealth();
		upgradeRefillObsidian = new UpgradeRefillObsidian();
		setUpgrades(upgradeClassEffect, upgradeDamageBase, upgradeDefenseBase, upgradeFireRatio, upgradeFireworks, upgradeHealth, upgradeRefillObsidian);
	}
	
	private void setUpgrades(Upgrade... upgrades) {
		this.upgrades = upgrades;
	}
	
	public final Upgrade[] getUpgrades() {
		return upgrades;
	}
	
	public final UpgradeClassEffect getUpgradeClassEffect() {
		return upgradeClassEffect;
	}
	
	public final Inventory getGui() {
		int size = CommonsHelper.getGuiSizeFromNumOfElements(upgrades);
		Inventory inventory = Bukkit.createInventory(null, size, Main.getInstance().getVillagerShopName());
		for (Upgrade upgrade : upgrades) {
			inventory.addItem(upgrade.getDisplayItem());
		}
		return inventory;
	}
	
	public abstract Shop copy();
}
