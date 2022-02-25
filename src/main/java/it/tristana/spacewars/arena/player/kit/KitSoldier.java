package it.tristana.spacewars.arena.player.kit;

import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.gun.GunSoldier;
import it.tristana.spacewars.config.SettingsKits;

public class KitSoldier extends Kit {

	public KitSoldier(SettingsKits settings) {
		super(settings);
	}

	@Override
	protected Gun forgeGun() {
		return new GunSoldier(settings);
	}
}
