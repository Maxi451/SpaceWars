package it.tristana.spacewars.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.team.ColorsHelper;
import it.tristana.spacewars.arena.team.Nexus;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> {

	private UsersManager<SpaceUser> usersManager;
	private List<Location> nexusLocations;

	public SpaceArena(World world, String name, PartiesManager partiesManager, UsersManager<SpaceUser> usersManager) {
		super(world, name, partiesManager);
		this.usersManager = usersManager;
		nexusLocations = new ArrayList<Location>();
	}

	@Override
	public void startGame() {
		super.startGame();
		selectRandomKitsIfNeeded();
		giveStartingItems();
	}

	@Override
	public void setStatus(Status status) {
		super.setStatus(status);
	}

	@Override
	protected SpacePlayer createArenaPlayer(Player player) {
		return new SpacePlayer(this, usersManager.getUser(player));
	}

	@Override
	protected SpaceTeam createTeam(int index) {
		return new SpaceTeam(this, "Team #" + index, null, new Nexus(nexusLocations.get(index)), ColorsHelper.getColor(index));
	}

	@Override
	protected int getTeamsForNumPlayers(int players) {
		return Math.min(ColorsHelper.MAX_SUPPORTED_TEAMS, Math.min(spawnpoints.size(), nexusLocations.size()));
	}
	
	public boolean onBlockBroken(Player player, Block block) {
		Material type = block.getType();
		boolean isNexus = type == Nexus.NEXUS_MATERIAL;
		if (!isNexus && type != Nexus.PILLAR_MATERIAL) {
			return false;
		}
		Function<SpaceTeam, Boolean> tester; 
		if (isNexus) {
			tester = team -> { return team.getNexus().breakNexus(); };
		} else {
			tester = team -> { return team.getNexus().breakPillar(block.getLocation()); };
		}
		for (SpaceTeam team : teams) {
			if (tester.apply(team)) {
				return true;
			}
		}
		return false;
	}

	private void selectRandomKitsIfNeeded() {
		players.forEach(player -> {
			if (player.getKit() == null) {
				player.setKit(null); // TODO
			}
		});
	}
	
	private void giveStartingItems() {
		teams.forEach(team -> team.getPlayers().forEach(spacePlayer -> {
			PlayerInventory inventory = spacePlayer.getPlayer().getInventory();
			inventory.setArmorContents(team.getArmor());
			spacePlayer.getKit().giveItems(inventory);
		}));
	}
}
