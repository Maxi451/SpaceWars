package it.tristana.spacewars.helper;

import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class AabbHelper {

	public static BoundingBox getFor(Player player) {
		BoundingBox result = player.getBoundingBox();
		if (player.isGliding()) {
			result.expand(0.25, 0, 0.25);
		}
		return result;
	}
}
