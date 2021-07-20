package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectTank;

public class ShopTank extends Shop {

	public ShopTank() {
		super(new UpgradeClassEffectTank());
	}

	@Override
	public Shop copy() {
		return new ShopTank();
	}
}
