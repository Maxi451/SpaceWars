package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectSoldier;

public class ShopSoldier extends Shop {

	public ShopSoldier() {
		super(new UpgradeClassEffectSoldier());
	}

	@Override
	public Shop copy() {
		return new ShopSoldier();
	}
}
