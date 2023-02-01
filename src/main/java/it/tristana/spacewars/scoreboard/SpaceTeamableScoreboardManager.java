package it.tristana.spacewars.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.helper.TeamsColors;
import it.tristana.commons.scoreboard.TeamableScoreboard;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.config.ConfigScoreboard;
import it.tristana.spacewars.config.SettingsScoreboard;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceTeamableScoreboardManager extends TeamableScoreboard<SpaceUser, SpaceArena, SpaceTeam> {

	private final SettingsScoreboard settings;

	public SpaceTeamableScoreboardManager(SpaceArena arena, SettingsScoreboard settings) {
		super(arena, settings::getGameName);
		this.settings = settings;
		reload();
	}

	@Override
	protected Objective createObjective(String name) {
		Objective spacewars = scoreboard.registerNewObjective(ChatColor.stripColor(name), Criteria.DUMMY, name);
		spacewars.setDisplaySlot(DisplaySlot.SIDEBAR);
		return spacewars;
	}

	@Override
	protected List<String> parseTeam(SpaceTeam team) {
		String[] lookingFor = new String[] { "{team color}", "{team name}", "{lives}", "{has nexus}" };
		String[] replacement = new String[] { team.getColorCode(), team.getName(), String.valueOf(team.getLives()), team.getNexus().isBroken() ? "&4&l\u274c" : "&2&l\u2705"};
		List<String> lines = new ArrayList<>();
		lines.add(CommonsHelper.replaceAll(settings.getGameTeam(), lookingFor, replacement));
		return lines;
	}

	@Override
	protected String parseLine(String line) {
		return line;
	}

	@Override
	protected void setTeamColor(Team team, Color color) {
		team.setColor(TeamsColors.fromColor(color));
	}

	@Override
	protected String getTeamsPlaceholder() {
		return ConfigScoreboard.TEAMS_PLACEHOLDER;
	}

	@Override
	protected String getScoreboardName() {
		return settings.getGameName();
	}

	@Override
	protected List<String> getScoreboardLines() {
		return settings.getGameLines();
	}
}
