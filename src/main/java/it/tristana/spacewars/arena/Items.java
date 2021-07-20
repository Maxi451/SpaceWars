package it.tristana.spacewars.arena;

import java.lang.reflect.Constructor;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.config.ItemsValues;
import it.tristana.spacewars.interfaces.Configurable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class Items implements Configurable {

	private ItemsValues itemsValues;
	
	public Items(ItemsValues itemsValues) {
		this.itemsValues = itemsValues;
	}
	
	private static Constructor<NBTTagString> constructor = null;
	static {
		try {
			constructor = NBTTagString.class.getDeclaredConstructor(String.class);
			constructor.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	private ItemStack pickaxe;
	private ItemStack missile;
	private ItemStack fuel;
	private ItemStack emp;
	private ItemStack wings;

	@Override
	public void setup() {
		pickaxe = setNameAndLore(setUnbreakable(new ItemStack(Material.DIAMOND_PICKAXE)), itemsValues.getPickaxeName(), itemsValues.getPickaxeLore());
		try {
			pickaxe = getCanBreakBeaconAndObsidian(pickaxe);
		} catch (Exception e) {
			CommonsHelper.consoleInfo("&cCan't create an instance of NBTTagString through reflection");
			e.printStackTrace();
		}
		missile = setNameAndLore(new ItemStack(Material.SPECTRAL_ARROW), itemsValues.getMissileName(), itemsValues.getMissileLore());
		fuel = setNameAndLore(new ItemStack(Material.FIREWORK_ROCKET), itemsValues.getFuelName(), itemsValues.getFuelLore());
		emp = setNameAndLore(new ItemStack(Material.ENDER_EYE), itemsValues.getEmpName(), itemsValues.getEmpLore());
		wings = setNameAndLore(setUnbreakable(new ItemStack(Material.ELYTRA)), itemsValues.getElytraName(), itemsValues.getElytraLore());
	}

	public ItemStack getPickaxe() {
		return get(pickaxe);
	}

	public ItemStack getMissile() {
		return get(missile);
	}

	public ItemStack getFuel() {
		return get(fuel);
	}

	public ItemStack getEmp() {
		return get(emp);
	}

	public ItemStack getWings() {
		return get(wings);
	}

	private static ItemStack get(ItemStack item) {
		return item.clone();
	}
	
	private static ItemStack setNameAndLore(ItemStack item, String name, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack setUnbreakable(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_DESTROYS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack getCanBreakBeaconAndObsidian(ItemStack item) throws Exception {
		net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
		NBTTagList idsTag = new NBTTagList();
		idsTag.add(constructor.newInstance("minecraft:obsidian"));
		idsTag.add(constructor.newInstance("minecraft:beacon"));
		NBTTagCompound tag = stack.hasTag() ? stack.getTag() : new NBTTagCompound();
		tag.set("CanDestroy", idsTag);
		stack.setTag(tag);
		return CraftItemStack.asBukkitCopy(stack);
	}
}
