package it.tristana.spacewars.arena.combact;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.SpaceTeam;
import it.tristana.spacewars.interfaces.Drawable;

public class HomingMissile implements Drawable {

	public static final double MISSILE_SIZE = 0.1;
	private static final double MAX_RANGE = 200;
	private static final double MAX_SPEED = 0.85;
	
	private final List<SpacePlayer> players;
	private SpacePlayer shooter;
	private SpacePlayer target;
	private final SpaceArena arena;
	private double remainingRange;
	private Location pos;
	private double speed;
	private double motionX;
	private double motionY;
	private double motionZ;
	
	public HomingMissile(final SpaceArena arena, final SpacePlayer shooter, final List<SpacePlayer> players) {
		this.players = players;
		this.shooter = shooter;
		this.arena = arena;
		pos = shooter.getPlayer().getEyeLocation();
		target = getTarget(shooter, players);
		setup();
		if (target == null) {
			setLookingDirection();
		}
	}
	
	public HomingMissile(final SpaceArena arena, final SpacePlayer target, final List<SpacePlayer> players, final Location missileSpawn) {
		this.players = players;
		this.shooter = null;
		this.arena = arena;
		this.target = target;
		pos = missileSpawn;
		setup();
	}
	
	private void setup() {
		speed = 1;
		remainingRange = MAX_RANGE;
	}
	
	private SpacePlayer getTarget(final SpacePlayer shooter, final List<SpacePlayer> players) {
		SpacePlayer target = null;
		if (shooter != null) {
			final Location pos = shooter.getPlayer().getEyeLocation();
			final SpaceTeam team = shooter.getTeam();
			double minDistance = MAX_RANGE;
			double targetDistance;
			for (final SpacePlayer test : players) {
				final Player testPlayer = test.getPlayer();
				if (testPlayer.getGameMode() == GameMode.SURVIVAL && test.getTeam() != team) {
					targetDistance = testPlayer.getEyeLocation().distance(pos);
					if (targetDistance < minDistance) {
						target = test;
						minDistance = targetDistance;
					}
				}
			}
		}
		return target;
	}
	
	private void setLookingDirection() {
		final Vector vector = shooter.getPlayer().getEyeLocation().getDirection();
		motionX = vector.getX() * speed;
		motionY = vector.getY() * speed;
		motionZ = vector.getZ() * speed;
	}
	
	/**
	 * Updates the missile position checking if it exploded
	 * @return true if the missile exploded
	 */
	
	public boolean runTick() {
		boolean exploded = false;
		updateSpeed();
		if (target == null) {
			target = getTarget(shooter, players);
		}
		if (target != null) {
			updateDirection();
		}
		exploded = moveMissile() || checkCollisions();
		return exploded;
	}

	private void updateSpeed() {
		final double oldSpeed = speed;
		speed *= 1.125;
		if (speed > MAX_SPEED) {
			speed = MAX_SPEED;
		}
		double ratio = speed / oldSpeed;
		motionX *= ratio;
		motionY *= ratio;
		motionZ *= ratio;
	}
	
	private void updateDirection() {
		final Location targetPos = target.getPlayer().getEyeLocation();
		final double diffX = Math.abs(pos.getX() - targetPos.getX());
		final double diffY = Math.abs(pos.getY() - targetPos.getY());
		final double diffZ = Math.abs(pos.getZ() - targetPos.getZ());
		final double sum = diffX + diffY + diffZ;
		final double weightX = diffX / sum;
		final double weightY = diffY / sum;
		final double weightZ = diffZ / sum;
		motionX = speed * (pos.getX() < targetPos.getX() ? weightX : -weightX);
		motionY = speed * (pos.getY() < targetPos.getY() ? weightY : -weightY);
		motionZ = speed * (pos.getZ() < targetPos.getZ() ? weightZ : -weightZ);
	}
	
	private boolean moveMissile() {
		boolean hasFuel = true;
		if (remainingRange - speed <= 0) {
			hasFuel = false;
			final double percentRemaining = remainingRange / speed;
			motionX *= percentRemaining / 100;
			motionY *= percentRemaining / 100;
			motionZ *= percentRemaining / 100;
		}
		else {
			remainingRange -= speed;
		}
		pos = new Location(pos.getWorld(), pos.getX() + motionX, pos.getY() + motionY, pos.getZ() + motionZ);
		return !hasFuel;
	}
	
	private boolean checkCollisions() {
		boolean collided = pos.getWorld().getBlockAt(pos).getType() != Material.AIR;;
		if (!collided) {
			SpacePlayer result = null;
			for (final SpacePlayer cachedPlayer : players) {
				if (cachedPlayer != shooter) {
					if (cachedPlayer.getPlayer().getEyeLocation().distance(pos) < 0.5) {
						result = cachedPlayer;
						break;
					}
				}
			}
			if (result != null) {
				pos = result.getPlayer().getEyeLocation().clone();
				collided = true;
			}
			else {
				collided = !arena.isInsideBorders(pos);
			}
		}
		return collided;
	}
	
	@Override
	public void draw() {
		final double x = pos.getX();
		final double y = pos.getY();
		final double z = pos.getZ();
		pos.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, x, y, z, 1, 0, 0, 0, 0, null, true);
	}
	
	public SpacePlayer getTarget() {
		return target;
	}
	
	public void removeTarget() {
		target = null;
	}
	
	public Location getLocation() {
		return pos;
	}
	
	public SpacePlayer getShooter() {
		return shooter;
	}
	
	public void removeShooter() {
		shooter = null;
	}
}
