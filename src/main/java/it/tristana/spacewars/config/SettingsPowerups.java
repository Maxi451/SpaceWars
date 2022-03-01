package it.tristana.spacewars.config;

import it.tristana.commons.config.Settings;
import it.tristana.commons.helper.CommonsHelper;

public class SettingsPowerups extends Settings<ConfigPowerups> {

	private String _1upName;
	private int _1upChance;
	
	private String longBarrelName;
	private int longBarrelChance;
	
	public SettingsPowerups(ConfigPowerups config) {
		super(config);
	}

	@Override
	public void reload() {
		_1upName = config.getString(ConfigPowerups._1_UP_NAME);
		_1upChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups._1_UP_CHANCE), 10);
		
		longBarrelName = config.getString(ConfigPowerups.LONG_BARREL_NAME);
		longBarrelChance = CommonsHelper.parseIntOrGetDefault(config.getString(ConfigPowerups.LONG_BARREL_CHANCE), 20);
	}

	public String get_1upName() {
		return _1upName;
	}

	public int get_1upChance() {
		return _1upChance;
	}

	public String getLongBarrelName() {
		return longBarrelName;
	}

	public int getLongBarrelChance() {
		return longBarrelChance;
	}
}
