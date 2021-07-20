package it.tristana.spacewars.helper;

import it.tristana.spacewars.arena.SpaceArena;

public class JoinHelper {

	public final SpaceArena arena;
	public final JoinResult joinResult;
	
	public JoinHelper(SpaceArena arena, JoinResult joinResult) {
		this.arena = arena;
		this.joinResult = joinResult;
	}
}
