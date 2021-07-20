package it.tristana.spacewars.arena.shop;

import it.tristana.spacewars.arena.upgrade.UpgradeClassEffectMiner;

public class ShopMiner extends Shop {

	public ShopMiner() {
		super(new UpgradeClassEffectMiner());
	}

	@Override
	public Shop copy() {
		return new ShopMiner();
	}
}
