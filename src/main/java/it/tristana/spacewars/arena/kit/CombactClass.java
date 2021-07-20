package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.Items;
import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.helper.Utility;
import it.tristana.spacewars.arena.shop.Shop;
import it.tristana.spacewars.config.CombactClassesValues;

public abstract class CombactClass {
	
	protected Items items;
	protected CombactClassesValues ccValues;
	
	private final String name;
	protected ItemStack[] classItems;
	protected final Gun gun;
	protected final Shop shop;
	private final Material icon;
	private final boolean needsPermission;

	public CombactClass(String name, Shop shop, Material icon, boolean needsPermission) {
		Main plugin = Main.getInstance();
		this.ccValues = plugin.getCombactClassesValues();
		this.items = plugin.getItems();
		this.name = CommonsHelper.toChatColors(name);
		this.gun = createGun();
		this.shop = shop;
		this.icon = icon;
		this.needsPermission = needsPermission;
		classItems = createItems();
	}

	public final Shop getNewShop() {
		return shop.copy();
	}

	public final String getName() {
		return name;
	}

	public final String getRawName() {
		return name;
	}
	
	public final ItemStack[] getClassItems() {
		return classItems;
	}

	public final Gun getGun() {
		return gun;
	}

	public final ItemStack getItemToShow() {
		final ItemStack item = createItemToShow();
		final ItemMeta itemMeta = item.getItemMeta();
		final List<String> lore = getDescription();
		String yes = ccValues.getYesWord();
		String no = ccValues.getNoWord();
		lore.add("");
		lore.add(ccValues.getInfoTitle());
		lore.add(CommonsHelper.replaceAll(ccValues.getInfoFireRatio(), "{fire ratio}", gun.getFireRatio() + ""));
		lore.add(CommonsHelper.replaceAll(ccValues.getInfoBaseDamage(), "{base damage}", gun.getBaseDamage() + ""));
		lore.add(CommonsHelper.replaceAll(ccValues.getInfoFmj(), "{has FMJ}", gun.hasFMJ() ? yes : no));
		lore.add(CommonsHelper.replaceAll(ccValues.getInfoLongBarrel(), "{has long barrel}", gun.hasLongBarrel() ? yes : no));
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return item;
	}

	protected abstract Gun createGun();
	
	public abstract List<String> getDescription();

	public final ItemStack createItemToShow() {
		ItemStack item = new ItemStack(icon);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(getName());
		item.setItemMeta(itemMeta);
		return item;
	}

	protected ItemStack[] createItems() {
		ItemStack pickaxe = items.getPickaxe();
		ItemStack[] items = new ItemStack[] {
				gun.getItemGun(),
				pickaxe
		};
		return items;
	}
	
	public double getDamageMultiplier() {
		return 1;
	}
	
	public double getDamageReduction(SpacePlayer arenaPlayer) {
		return 0;
	}
	
	public void runStartActions(SpacePlayer cachedPlayer) {}
	
	public final boolean needsPermission() {
		return needsPermission;
	}
	
	protected static int parseIntOrGetDefault(String line, int defaultValue) {
		return Utility.parseIntOrGetDefault(line, defaultValue);
	}
	
	protected static double parseDouble(String line, double defaultValue) {
		return Utility.parseDoubleOrGetDefault(line, defaultValue);
	}
}
