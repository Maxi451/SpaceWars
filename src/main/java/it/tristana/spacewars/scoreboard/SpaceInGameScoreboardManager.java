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
import it.tristana.spacewars.config.SettingsSpaceScoreboard;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceInGameScoreboardManager extends TeamableScoreboard<SettingsSpaceScoreboard, SpaceUser, SpaceArena, SpaceTeam> {

	public SpaceInGameScoreboardManager(SpaceArena arena, SettingsSpaceScoreboard settings) {
		super(arena, settings);
	}

	@Override
	public void addUser(SpaceUser user) {
		super.addUser(user);
	}

	@Override
	public void removeUser(SpaceUser user) {
		super.removeUser(user);
	}

	@Override
	protected Objective createObjective() {
		Objective spacewars = scoreboard.registerNewObjective("spacewarsgame", Criteria.DUMMY, settings.getName());
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
}
