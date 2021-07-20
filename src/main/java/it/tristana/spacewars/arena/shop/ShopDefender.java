package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectDefender;

public class ShopDefender extends Shop {

	public ShopDefender() {
		super(new UpgradeClassEffectDefender());
	}

	@Override
	public Shop copy() {
		return new ShopDefender();
	}
}
