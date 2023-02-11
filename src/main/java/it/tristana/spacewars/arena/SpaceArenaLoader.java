package it.tristana.spacewars.arena;

import java.io.File;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import it.tristana.commons.arena.loader.BasicArenaLoader;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.arena.Arena;
import it.tristana.commons.interfaces.util.VillagerShop;
import it.tristana.spacewars.Main;

public class SpaceArenaLoader extends BasicArenaLoader<SpaceArena> {

	private static final String NEXUSES = "nexuses";
	private static final String SPHERES = "spheres";
	private static final String SHOPS = "shops";

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

		saveSection(arena, SPAWNPOINTS, arena.getSpawnpoints(), t -> t);
		saveSection(arena, NEXUSES, arena.getNexusLocations(), t -> t);
		saveSection(arena, SPHERES, arena.getSpheres(), SpherePowerup::getLocation);
		saveSection(arena, SHOPS, arena.getShops(), VillagerShop::getLocation);
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

		loadSection(arena, SPAWNPOINTS, arena::setSpawnpoint);
		loadSection(arena, NEXUSES, arena::setNexusLocation);
		loadSection(arena, SPHERES, arena::setSpherePowerup);
		loadSection(arena, SHOPS, arena::addShop);
		return arena;
	}

	private <T> void saveSection(SpaceArena arena, String sectionName, Collection<T> source, Function<T, Location> locationRetriever) {
		String root = getRoot(arena) + sectionName + ".";
		int counter = 0;
		for (T obj : source) {
			setLocation(root + counter, locationRetriever.apply(obj));
			counter ++;
		}
	}

	private void loadSection(SpaceArena arena, String sectionName, Consumer<Location> reader) {
		String root = getRoot(arena) + sectionName;
		World world = arena.getWorld();
		ConfigurationSection section = fileConfiguration.getConfigurationSection(root);
		if (section != null) {
			new TreeSet<>(section.getKeys(false)).forEach(key -> {
				reader.accept(getLocation(root + "." + key, world));
			});
		}
	}

	private String getRoot(Arena<?> arena) {
		return getRoot(arena.getName());
	}
}
