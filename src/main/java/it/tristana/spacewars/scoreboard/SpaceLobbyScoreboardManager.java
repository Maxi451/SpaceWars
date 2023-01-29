package it.tristana.spacewars.scoreboard;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import it.tristana.commons.scoreboard.BasicScoreboardManager;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.config.SettingsSpaceScoreboard;
import it.tristana.spacewars.database.SpaceUser;
import me.clip.placeholderapi.PlaceholderAPI;

public class SpaceLobbyScoreboardManager extends BasicScoreboardManager<SpaceUser, SettingsSpaceScoreboard> {

	private Main plugin;
	private SettingsSpaceScoreboard settings;

	public SpaceLobbyScoreboardManager(SettingsSpaceScoreboard settings, Main plugin) {
		super(settings);
		this.plugin = plugin;
		this.settings = settings;
	}

	@Override
	public Collection<Objective> createObjectives(SpaceUser user, Scoreboard scoreboard) {
		Collection<Objective> objectives = new HashSet<>();
		Objective spacewars = scoreboard.registerNewObjective("spacewarslobby", Criteria.DUMMY, settings.getName());
		spacewars.setDisplaySlot(DisplaySlot.SIDEBAR);
		updateObjective(user, spacewars);
		objectives.add(spacewars);
		return objectives;
	}

	@Override
	protected String parseLine(SpaceUser user, String line) {
		return plugin.isPapiEnabled() ? PlaceholderAPI.setPlaceholders(user.getOnlinePlayer(), line) : line;
	}
}
