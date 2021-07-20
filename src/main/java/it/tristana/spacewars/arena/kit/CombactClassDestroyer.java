package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;

import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopDestroyer;

public class CombactClassDestroyer extends CombactClass {

	public CombactClassDestroyer() {
		super("Destroyer", new ShopDestroyer(), Material.TNT, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getDestroyerDescription();
	}
	
	@Override
	public double getDamageReduction(SpacePlayer arenaPlayer) {
		return -35;
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getDestroyerName(), Material.GOLDEN_HOE, parseDouble(ccValues.getDestroyerFireRatio(), 0.3), parseDouble(ccValues.getDestroyerDamage(), 14), false, false);
	}
}
