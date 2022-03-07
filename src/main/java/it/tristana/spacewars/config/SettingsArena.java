package it.tristana.spacewars.config;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsArena extends Settings<ConfigArena> {

	private double flyIntoWallDamageReductionPercentage;
	
	public SettingsArena(ConfigArena config) {
		super(config);
	}

	@Override
	public void reload() {
		flyIntoWallDamageReductionPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigArena.FLY_INTO_WALL_DAMAGE_REDUCTION_PERCENTAGE), 50) / 100;
	}

	public double getFlyIntoWallDamageReductionPercentage() {
		return flyIntoWallDamageReductionPercentage;
	}
}
