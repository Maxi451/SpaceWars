package it.tristana.spacewars.helper;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTList;

public class ItemUtils {

	private ItemUtils() {}
	
	public static ItemStack forgeUnbreakableItem(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack forgeCanDestroyItem(ItemStack item, String... blocks) {
		NBTItem nbt = new NBTItem(item);
		NBTList<String> canBreak = nbt.getStringList("CanDestroy");
		for (int i = 0; i < blocks.length; i ++) {
			canBreak.add(blocks[i]);
		}
		return nbt.getItem();
	}
}
