package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectSniper;

public class ShopSniper extends Shop {

	public ShopSniper() {
		super(new UpgradeClassEffectSniper());
	}

	@Override
	public Shop copy() {
		return new ShopSniper();
	}
}
