package it.tristana.spacewars.scoreboard;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import it.tristana.commons.scoreboard.BasicScoreboardManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.database.SpaceUser;
import me.clip.placeholderapi.PlaceholderAPI;

public class SpacePersonalScoreboardManager extends BasicScoreboardManager<SpaceUser> {

	private final Main plugin;
	private final Supplier<String> nameSupplier;
	private final Supplier<List<String>> linesSupplier;

	public SpacePersonalScoreboardManager(Main plugin, Supplier<String> nameSupplier, Supplier<List<String>> linesSupplier) {
		this.plugin = plugin;
		this.nameSupplier = nameSupplier;
		this.linesSupplier = linesSupplier;
		reload();
	}

	@Override
	public Collection<Objective> createObjectives(SpaceUser user, Scoreboard scoreboard) {
		Collection<Objective> objectives = new HashSet<>();
		String name = getScoreboardName();
		Objective spacewars = scoreboard.registerNewObjective(ChatColor.stripColor(name), Criteria.DUMMY, name);
		spacewars.setDisplaySlot(DisplaySlot.SIDEBAR);
		updateObjective(user, spacewars);
		objectives.add(spacewars);
		return objectives;
	}

	@Override
	protected String parseLine(SpaceUser user, String line) {
		return plugin.isPapiEnabled() ? PlaceholderAPI.setPlaceholders(user.getOnlinePlayer(), line) : line;
	}

	@Override
	protected String getScoreboardName() {
		return nameSupplier.get();
	}

	@Override
	protected List<String> getScoreboardLines() {
		return linesSupplier.get();
	}
}
