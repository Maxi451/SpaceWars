package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;

import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopDefender;

public class CombactClassDefender extends CombactClass {

	public CombactClassDefender() {
		super("Defender", new ShopDefender(), Material.SHIELD, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getDefenderDescription();
	}
	
	@Override
	public double getDamageReduction(SpacePlayer arenaPlayer) {
		return arenaPlayer.isNearOwnNexus() ? 15 : 0;
	}
	
	@Override
	public double getDamageMultiplier() {
		double damage = 1;
		if (shop.getUpgradeClassEffect().isUnlocked()) {
			damage = 1.1;
		}
		return damage;
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getDefenderName(), Material.GOLDEN_HOE, parseDouble(ccValues.getDefenderFireRatio(), 1.7), parseDouble(ccValues.getDefenderDamage(), 3.5), false, false);
	}
}
