package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;

import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopSoldier;

public class CombactClassSoldier extends CombactClass {

	public CombactClassSoldier() {
		super("Soldier", new ShopSoldier(), Material.SNOWBALL, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getSoldierDescription();
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getSoldierName(), Material.IRON_HOE, parseDouble(ccValues.getSoldierFireRatio(), 2), parseDouble(ccValues.getSoldierDamage(), 4), false, false);
	}
}
