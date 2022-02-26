package it.tristana.spacewars.arena.player.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTList;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.config.SettingsKits;

public abstract class Kit {

	protected SettingsKits settings;
	private Gun gun;
	
	public Kit(SettingsKits settings) {
		this.settings = settings;
	}
	
	public final void giveItems(Inventory inventory) {
		inventory.addItem(getGun().getItem(), forgeCanDestroyItem(getPickaxe()));
	}
	
	public final Gun getGun() {
		if (gun == null) {
			gun = forgeGun();
		}
		return gun;
	}
	
	protected ItemStack getPickaxe() {
		return new ItemStack(Material.DIAMOND_PICKAXE);
	}
	
	private static ItemStack forgeCanDestroyItem(ItemStack item) {
		NBTItem nbt = new NBTItem(item);
		NBTList<String> canBreak = nbt.getStringList("CanDestroy");
		canBreak.add("obsidian");
		canBreak.add("beacon");
		return nbt.getItem();
	}
	
	public abstract ItemStack getRawDisplayItem();
	
	public abstract String getName();
	
	public abstract List<String> getLore();
	
	protected abstract Gun forgeGun();
}
