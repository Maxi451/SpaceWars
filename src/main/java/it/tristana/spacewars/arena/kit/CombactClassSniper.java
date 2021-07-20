package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;

import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopSniper;

public class CombactClassSniper extends CombactClass {

	public CombactClassSniper() {
		super("Sniper", new ShopSniper(), Material.SPYGLASS, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getSniperDescription();
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getSniperName(), Material.DIAMOND_HOE, parseDouble(ccValues.getSniperFireRatio(), 0.55), parseDouble(ccValues.getSniperDamage(), 11), false, true);
	}
}
