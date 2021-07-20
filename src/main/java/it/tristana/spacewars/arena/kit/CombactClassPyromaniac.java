package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;

import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopPyromaniac;

public class CombactClassPyromaniac extends CombactClass {

	public CombactClassPyromaniac() {
		super("Pyromaniac", new ShopPyromaniac(), Material.BLAZE_POWDER, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getPyromaniacDescription();
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getPyromaniacName(), Material.GOLDEN_HOE, parseDouble(ccValues.getPyromaniacFireRatio(), 3), parseDouble(ccValues.getPyromaniacDamage(), 2.5), false, false);
	}
}
