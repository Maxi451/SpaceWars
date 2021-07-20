package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopMiner;

public class CombactClassMiner extends CombactClass {
	
	public CombactClassMiner() {
		super("Miner", new ShopMiner(), Material.DIAMOND_PICKAXE, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getMinerDescription();
	}

	@Override
	protected ItemStack[] createItems() {
		ItemStack pickaxe = Main.getInstance().getItems().getPickaxe();
		pickaxe.addEnchantment(Enchantment.DIG_SPEED, 3);
		ItemStack[] items = new ItemStack[] {
				gun.getItemGun(),
				pickaxe
		};
		return items;
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getMinerName(), Material.WOODEN_HOE, parseDouble(ccValues.getMinerFireRatio(), 1.4), parseDouble(ccValues.getMinerDamage(), 3.25), false, false);
	}
}
