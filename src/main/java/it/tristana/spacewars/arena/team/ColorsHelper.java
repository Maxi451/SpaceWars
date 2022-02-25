package it.tristana.spacewars.arena.team;

import org.bukkit.Color;

public class ColorsHelper {

	private static final Color[] colors = new Color[] {
			Color.RED,
			Color.BLUE,
			Color.GREEN,
			Color.YELLOW,
			Color.AQUA,
			Color.WHITE,
			Color.FUCHSIA,
			Color.GRAY
	};
	
	public static final int MAX_SUPPORTED_TEAMS = colors.length;
	
	private ColorsHelper() {}
	
	public static Color getColor(int teamIndex) {
		return teamIndex >= 0 || teamIndex < MAX_SUPPORTED_TEAMS ? colors[teamIndex] : null;
	}
}
