package it.tristana.spacewars.config;

import java.io.File;

import it.tristana.commons.config.Config;

public class ConfigArena extends Config {

	public static final String FLY_INTO_WALL_DAMAGE_REDUCTION_PERCENTAGE = "fly-into-wall-damage-reduction-percentage";
	public static final String STARTING_LIVES_PER_PLAYER = "starting-lives-per-player";

	public ConfigArena(File folder) {
		super(new File(folder, "arena.yml"));
	}

	@Override
	protected void createDefault() {
		set(FLY_INTO_WALL_DAMAGE_REDUCTION_PERCENTAGE, "50");
		set(STARTING_LIVES_PER_PLAYER, "5");
	}
}
