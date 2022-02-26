package it.tristana.spacewars.arena;

import java.io.File;

import it.tristana.commons.arena.loader.BasicArenaLoader;

public class SpaceArenaLoader extends BasicArenaLoader<SpaceArena> {

	public SpaceArenaLoader(File folder) {
		super(new File(folder, "arena.yml"));
	}

	@Override
	public void saveArena(SpaceArena arena) {
	}

	@Override
	public SpaceArena loadArena(String name) {
		return null;
	}
}
