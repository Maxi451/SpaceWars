package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.scoreboard.ConfigTeamScoreboard;

public class ConfigSpaceScoreboard extends ConfigTeamScoreboard {

	public static final String IS_ENABLED = "is-enabled";

	public ConfigSpaceScoreboard(File folder) {
		super(new File(folder, "scoreboard.yml"));
	}

	@Override
	protected void createDefault() {
		set(NAME, "Scoreboard");
		set(LINES, Arrays.asList("line1", "line", "line3"));
		set(IS_ENABLED, "true");
	}
}
