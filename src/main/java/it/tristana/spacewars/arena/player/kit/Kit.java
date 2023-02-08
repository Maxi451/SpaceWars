package it.tristana.spacewars.arena.player.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import it.tristana.commons.interfaces.Reloadable;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.config.SettingsKits;
import it.tristana.spacewars.helper.ItemUtils;

public abstract class Kit implements Reloadable {

	public static final Material PICKAXE_MATERIAL = Material.DIAMOND_PICKAXE;
	
	protected SettingsKits settings;
	
	private Gun gun;
	private ItemStack pickaxe;
	private double armor;
	
	public Kit(SettingsKits settings) {
		this.settings = settings;
	}
	
	@Override
	public final void reload() {
		gun.reload();
		armor = getBaseArmor();
	}
	
	public final void giveItems(Inventory inventory) {
		inventory.addItem(getGun().getItem(), getPickaxe());
	}
	
	public final Gun getGun() {
		if (gun == null) {
			gun = forgeGun();
		}
		return gun;
	}
	
	public double getArmor(SpacePlayer player) {
		return armor;
	}
	
	public final void upgradePickaxe() {
		ItemStack pickaxe = getPickaxe();
		pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1);
	}
	
	public final ItemStack getPickaxe() {
		if (pickaxe == null) {
			pickaxe = ItemUtils.forgeUnbreakableItem(ItemUtils.forgeCanDestroyItem(getRawPickaxe(), "obsidian", "beacon"));
		}
		return pickaxe;
	}
	
	protected ItemStack getRawPickaxe() {
		return new ItemStack(PICKAXE_MATERIAL);
	}
	
	public abstract ItemStack getRawDisplayItem();
	
	public abstract String getName();
	
	public abstract List<String> getLore();
	
	protected abstract double getBaseArmor();
	
	protected abstract Gun forgeGun();
}
