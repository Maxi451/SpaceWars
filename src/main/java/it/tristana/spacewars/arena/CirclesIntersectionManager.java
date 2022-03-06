package it.tristana.spacewars.arena;

import java.util.Collection;
import java.util.List;

import org.bukkit.entity.Player;

import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.arena.player.SpacePlayer;

public class CirclesIntersectionManager implements Tickable {

	private static final double MAX_SPEED_MULTIPLIER = 2;
	
	private SpaceArena arena;
	private List<CirclePowerup> circles;
	private Collection<SpacePlayer> players;
	
	public CirclesIntersectionManager(SpaceArena arena) {
		this.arena = arena;
		this.circles = arena.getCirclesWithoutCopy();
		this.players = arena.getPlayersWithoutCopy();
	}
	
	@Override
	public void runTick() {
		for (SpacePlayer spacePlayer : players) {
			for (CirclePowerup circle : circles) {
				Player player = spacePlayer.getPlayer();
				if (circle.intersects(player, 0)) {
					if (circle.tryToPickUp()) {
						arena.giveRandomPowerup(spacePlayer);
						player.setVelocity(player.getVelocity().multiply(MAX_SPEED_MULTIPLIER - circle.getLocation().distance(player.getLocation()) / CirclePowerup.RADIUS));
					}
					break;
				}
			}
		}
	}
}
