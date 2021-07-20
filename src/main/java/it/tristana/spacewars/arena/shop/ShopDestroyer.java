package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectDestroyer;

public class ShopDestroyer extends Shop {

	public ShopDestroyer() {
		super(new UpgradeClassEffectDestroyer());
	}

	@Override
	public Shop copy() {
		return new ShopDestroyer();
	}
}
