package it.tristana.spacewars.helper;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import it.tristana.commons.arena.loader.BasicArenaLoader;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.math.CachedCircleEuclidean;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.Nexus;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.powerup.CirclePowerup;
import it.tristana.spacewars.config.ArenaValues;

public class SpaceArenaLoader extends BasicArenaLoader<SpaceArena> {
	
	private static final String NEXUSES = "nexuses";
	private static final String SPAWNPOINTS = "spawnpoints";
	private static final String POWERUPS = "powerups";
	private static final String ROTATION = "rotation";
	private static final String LOW_POS = "low-pos";
	private static final String HIGH_POS = "high-pos";
	
	private Main plugin;
	private ArenaValues arenaValues;
	private PartiesManager partiesManager;
	
	public SpaceArenaLoader(Main plugin, ArenaValues arenaValues, List<SpaceArena> arenas, File file, PartiesManager partiesManager) {
		super(arenas, file);
		this.plugin = plugin;
		this.arenaValues = arenaValues;
		this.partiesManager = partiesManager;
	}

	@Override
	public SpaceArena loadArena(String name) {
		SpaceArena arena;
		try {
			String root = getRoot(name);
			World world = Bukkit.getWorld(fileConfiguration.getString(root + WORLD));
			arena = new SpaceArena(plugin, arenaValues, world, name, partiesManager);
			ConfigurationSection currentSection = fileConfiguration.getConfigurationSection(root + SPAWNPOINTS);
			String sub;
			if (currentSection != null) {
				sub = root + SPAWNPOINTS + ".";
				for (String key : currentSection.getKeys(false)) {
					arena.setSpawnpoint(getLocation(sub + key, world));
				}
			}
			currentSection = fileConfiguration.getConfigurationSection(root + NEXUSES);
			if (currentSection != null) {
				sub = root + NEXUSES + ".";
				for (String key : currentSection.getKeys(false)) {
					arena.setNexus(getLocation(sub + key, world));
				}
			}
			currentSection = fileConfiguration.getConfigurationSection(root + POWERUPS);
			if (currentSection != null) {
				sub = root + ROTATION + ".";
				for (String key : currentSection.getKeys(false)) {
					arena.addPowerupCircle(getLocation(sub + key, world), Double.parseDouble(fileConfiguration.getString(sub + key + "." + ROTATION)));
				}
			}
			currentSection = fileConfiguration.getConfigurationSection(root + LOW_POS);
			if (currentSection != null) {
				arena.setLowerPos(getVector(root + LOW_POS));
			}
			currentSection = fileConfiguration.getConfigurationSection(root + HIGH_POS);
			if (currentSection != null) {
				arena.setUpperPos(getVector(root + HIGH_POS));
			}
		} catch (Exception e) {
			e.printStackTrace();
			arena = null;
		}
		return arena;
	}
	
	@Override
	public void saveArena(SpaceArena arena) {
		super.saveArena(arena);
		String root = getRoot(arena.getName());
		String sub = root + SPAWNPOINTS + ".";
		int counter = 0;
		for (Location spawnpoint : arena.getSpawnpoints()) {
			setLocation(sub + counter ++, spawnpoint);
		}
		sub = root + NEXUSES + ".";
		counter = 0;
		for (Nexus nexus : arena.getNexuses()) {
			setLocation(sub + counter ++, nexus.getLocation());
		}
		sub = root + POWERUPS + ".";
		counter = 0;
		for (CirclePowerup circlePowerup : arena.getPowerupsCircles()) {
			CachedCircleEuclidean circle = circlePowerup.getCircle();
			setLocation(sub + counter, circle.getCenter());
			set(sub + counter + "." + ROTATION, circle.getRotation());
		}
		setVector(root + LOW_POS + ".", arena.getLowerPos());
		setVector(root + HIGH_POS + ".", arena.getUpperPos());
	}
	
	private Vector getVector(String path) {
		if (!path.endsWith(".")) {
			path += ".";
		}
		double x = Double.parseDouble(fileConfiguration.getString(path + X));
		double y = Double.parseDouble(fileConfiguration.getString(path + Y));
		double z = Double.parseDouble(fileConfiguration.getString(path + Z));
		return new Vector(x, y, z);
	}
	
	private void setVector(String path, Vector vector) {
		if (!path.endsWith(".")) {
			path += ".";
		}
		fileConfiguration.set(path + X, vector.getX());
		fileConfiguration.set(path + Y, vector.getY());
		fileConfiguration.set(path + Z, vector.getZ());
	}
}
