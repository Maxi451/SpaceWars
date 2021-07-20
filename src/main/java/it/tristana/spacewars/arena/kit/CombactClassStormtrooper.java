package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.Items;
import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopStormtrooper;

public class CombactClassStormtrooper extends CombactClass {
	
	public CombactClassStormtrooper() {
		super("Stormtrooper", new ShopStormtrooper(), Material.ENDER_EYE, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getStormtrooperDescription();
	}

	@Override
	protected ItemStack[] createItems() {
		Items itemsClass = Main.getInstance().getItems();
		ItemStack fuel = itemsClass.getFuel();
		fuel.setAmount(3);
		ItemStack[] items = new ItemStack[] {
				gun.getItemGun(),
				itemsClass.getPickaxe(),
				fuel,
				itemsClass.getEmp()
		};
		return items;
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getStormtrooperName(), Material.STONE_HOE, parseDouble(ccValues.getStormtrooperFireRatio(), 1.5), parseDouble(ccValues.getStormtrooperDamage(), 3.25), true, false);
	}
}
