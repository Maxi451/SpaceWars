package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectStormtrooper;

public class ShopStormtrooper extends Shop {

	public ShopStormtrooper() {
		super(new UpgradeClassEffectStormtrooper());
	}

	@Override
	public Shop copy() {
		return new ShopStormtrooper();
	}
}
