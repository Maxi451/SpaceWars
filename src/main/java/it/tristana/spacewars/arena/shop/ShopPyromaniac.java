package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectPyromaniac;

public class ShopPyromaniac extends Shop {

	public ShopPyromaniac() {
		super(new UpgradeClassEffectPyromaniac());
	}

	@Override
	public Shop copy() {
		return new ShopPyromaniac();
	}
}
