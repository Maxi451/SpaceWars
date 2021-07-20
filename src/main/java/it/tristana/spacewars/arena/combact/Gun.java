package it.tristana.spacewars.arena.combact;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun {
	
	private final Material material;
	private final String name;
	private final double fireRatio;
	private final double baseDamage;
	private final boolean hasFMJ;
	private final boolean hasLongBarrel;
	
	public Gun(String name, Material material, double fireRatio, double baseDamage, boolean hasFMJ, boolean hasLongBarrel) {
		this.name = name;
		this.material = material;
		this.fireRatio = fireRatio;
		this.baseDamage = baseDamage;
		this.hasFMJ = hasFMJ;
		this.hasLongBarrel = hasLongBarrel;
	}

	protected static double parseDoubleOrDefault(String value, double defaultValue) {
		double result;
		try {
			result = Double.parseDouble(value);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}
	
	public final Material getMaterial() {
		return material;
	}

	public final String getName() {
		return name;
	}

	public final double getFireRatio() {
		return fireRatio;
	}

	public final double getBaseDamage() {
		return baseDamage;
	}

	public final boolean hasFMJ() {
		return hasFMJ;
	}

	public final boolean hasLongBarrel() {
		return hasLongBarrel;
	}

	public final ItemStack getItemGun() {
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		return item;
	}
}
