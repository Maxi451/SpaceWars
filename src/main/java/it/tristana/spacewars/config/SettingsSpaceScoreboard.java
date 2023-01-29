package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.scoreboard.SettingsScoreboard;

public class SettingsSpaceScoreboard extends SettingsScoreboard<ConfigSpaceScoreboard> {

	private boolean isEnabled;

	public SettingsSpaceScoreboard(File folder) {
		super(folder, ConfigSpaceScoreboard.class);
	}

	@Override
	protected void reload(ConfigSpaceScoreboard config) {
		super.reload(config);
		isEnabled = CommonsHelper.parseBoolean(config.getString(ConfigSpaceScoreboard.IS_ENABLED));
	}

	public boolean isEnabled() {
		return isEnabled;
	}
}
