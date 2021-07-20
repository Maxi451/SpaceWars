package it.tristana.spacewars.arena.combact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.SpacePlayer;

public final class MissilesManager implements Tickable {

	private static final double EXPLOSION_RADIUS = 8;
	private static final double EXPLOSION_DAMAGE = 32;
	
	private final List<HomingMissile> missiles;
	private final SpaceArena arena;
	private int taskId;
	
	public MissilesManager(final SpaceArena arena) {
		this.arena = arena;
		missiles = Collections.synchronizedList(new ArrayList<HomingMissile>());
	}
	
	public void register(final int taskId) {
		this.taskId = taskId;
	}
	
	public void unregister() {
		Bukkit.getScheduler().cancelTask(taskId);
	}
	
	@Override
	public void runTick() {
		for (int i = 0; i < missiles.size(); i ++) {
			HomingMissile missile = missiles.get(i);
			boolean exploded = missile.runTick();
			Location location = missile.getLocation();
			if (exploded) {
				onMissileExplosion(missile.getShooter(), location);
				missiles.remove(i);
				i --;
			}
			else {
				missile.draw();
			}
		}
	}
	
	private void onMissileExplosion(final SpacePlayer shooter, final Location location) {
		arena.createDamagingExplosion(shooter, location, EXPLOSION_RADIUS, EXPLOSION_DAMAGE);
	}
	
	public void addMissile(final HomingMissile missile) {
		missiles.add(missile);
	}
	
	public void removeTargetPlayer(final SpacePlayer cachedPlayer) {
		for (HomingMissile missile : missiles) {
			if (missile.getTarget() == cachedPlayer) {
				missile.removeTarget();
			}
		}
	}
	
	public void removeShootingPlayer(final SpacePlayer cachedPlayer) {
		for (final HomingMissile missile : missiles) {
			if (missile.getShooter() == cachedPlayer) {
				missile.removeShooter();
			}
		}
	}
}
