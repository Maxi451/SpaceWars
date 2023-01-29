package it.tristana.spacewars.arena;

import java.io.File;
import java.util.List;
import java.util.TreeSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import it.tristana.commons.arena.loader.BasicArenaLoader;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.spacewars.Main;

public class SpaceArenaLoader extends BasicArenaLoader<SpaceArena> {

	private static final String NEXUSES = "nexuses";
	private static final String SPHERES = "spheres";

	private final Main plugin;

	public SpaceArenaLoader(File folder, Main plugin) {
		super(new File(folder, "saved-maps.yml"));
		this.plugin = plugin;
	}

	@Override
	public void saveArena(SpaceArena arena) {
		String root = getRoot(arena.getName());
		set(root + WORLD, arena.getWorld().getName());
		Location lobby = arena.getLobby();
		if (lobby != null) {
			setLocation(root + LOBBY, lobby);
		}
		List<Location> spawnpoints = arena.getSpawnpoints();
		int size = spawnpoints.size();
		for (int i = 0; i < size; i ++) {
			setLocation(root + SPAWNPOINTS + "." + i, spawnpoints.get(i));
		}
		List<Location> nexusLocations = arena.getNexusLocations();
		size = nexusLocations.size();
		for (int i = 0; i < size; i ++) {
			setLocation(root + NEXUSES + "." + i, nexusLocations.get(i));
		}
		int counter = 0;
		for (SpherePowerup sphere : arena.getSpheres()) {
			String path = root + SPHERES + "." + counter;
			setLocation(path, sphere.getLocation());
		}
	}

	@Override
	public SpaceArena loadArena(String name) {
		String root = getRoot(name);
		String worldName = fileConfiguration.getString(root + WORLD);
		World world = Bukkit.getWorld(worldName);
		if (world == null) {
			CommonsHelper.consoleInfo("&cWorld " + worldName + " for arena " + name + " not found!");
			return null;
		}
		SpaceArena arena = new SpaceArena(world, name, plugin);
		ConfigurationSection section = fileConfiguration.getConfigurationSection(root + LOBBY);
		if (section != null) {
			arena.setLobby(getLocation(root + LOBBY, world));
		}
		section = fileConfiguration.getConfigurationSection(root + SPAWNPOINTS);
		if (section != null) {
			new TreeSet<>(section.getKeys(false)).forEach(key -> {
				arena.setSpawnpoint(getLocation(root + SPAWNPOINTS + "." + key, world));
			});
		}
		section = fileConfiguration.getConfigurationSection(root + NEXUSES);
		if (section != null) {
			new TreeSet<>(section.getKeys(false)).forEach(key -> {
				arena.setNexusLocation(getLocation(root + NEXUSES + "." + key, world));
			});
		}
		section = fileConfiguration.getConfigurationSection(root + SPHERES);
		if (section != null) {
			new TreeSet<>(section.getKeys(false)).forEach(key -> {
				String path = root + SPHERES + "." + key;
				arena.setSpherePowerup(getLocation(path, world));
			});
		}
		return arena;
	}
}
