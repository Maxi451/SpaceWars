package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsArena extends Settings<ConfigArena> {

	private double flyIntoWallDamageReductionPercentage;
	
	public SettingsArena(File folder) {
		super(folder, ConfigArena.class);
	}

	@Override
	protected void reload(ConfigArena config) {
		flyIntoWallDamageReductionPercentage = CommonsHelper.parseDoubleOrGetDefault(config.getString(ConfigArena.FLY_INTO_WALL_DAMAGE_REDUCTION_PERCENTAGE), 50) / 100;
	}

	public double getFlyIntoWallDamageReductionPercentage() {
		return flyIntoWallDamageReductionPercentage;
	}
}
