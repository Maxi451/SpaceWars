package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;

import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopTraceur;

public final class CombactClassTraceur extends CombactClass {

	public CombactClassTraceur() {
		super("Traceur", new ShopTraceur(), Material.RABBIT_FOOT, true);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getTraceurDescription();
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getTraceurName(), Material.NETHERITE_HOE, parseDouble(ccValues.getTraceurFireRatio(), 1.75), parseDouble(ccValues.getTraceurDamage(), 3), false, false);
	}
}
