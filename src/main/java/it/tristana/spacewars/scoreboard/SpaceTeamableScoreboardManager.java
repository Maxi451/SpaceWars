package it.tristana.spacewars.scoreboard;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

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
		super(arena);
		this.settings = settings;
	}

	@Override
	protected Objective createObjective() {
		Objective spacewars = scoreboard.registerNewObjective("spacewarsgame", Criteria.DUMMY, getScoreboardName());
		spacewars.setDisplaySlot(DisplaySlot.SIDEBAR);
		return spacewars;
	}

	@Override
	protected List<String> parseTeam(SpaceTeam team) {
		return Arrays.asList(team.getName(), Integer.toHexString(team.getArmorColor().asRGB()));
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
