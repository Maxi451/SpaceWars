package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectTraceur;

public final class ShopTraceur extends Shop {

	public ShopTraceur() {
		super(new UpgradeClassEffectTraceur());
	}

	@Override
	public Shop copy() {
		return new ShopTraceur();
	}

}
