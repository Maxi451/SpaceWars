package it.tristana.spacewars.arena;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import it.tristana.commons.arena.BasicEnclosedArena;
import it.tristana.commons.interfaces.arena.Status;
import it.tristana.commons.interfaces.arena.player.PartiesManager;
import it.tristana.commons.interfaces.database.UsersManager;
import it.tristana.commons.interfaces.gui.ClickedGuiManager;
import it.tristana.commons.math.AABB;
import it.tristana.commons.math.RayTrace;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.kit.KitsManager;
import it.tristana.spacewars.arena.team.ColorsHelper;
import it.tristana.spacewars.arena.team.Nexus;
import it.tristana.spacewars.arena.team.SpaceTeam;
import it.tristana.spacewars.database.SpaceUser;
import it.tristana.spacewars.gui.GuiKit;
import it.tristana.spacewars.helper.ParticlesHelper;

public class SpaceArena extends BasicEnclosedArena<SpaceTeam, SpacePlayer> {

	private UsersManager<SpaceUser> usersManager;
	private List<Location> nexusLocations;
	private ClickedGuiManager guiManager;
	private KitsManager kitsManager;

	public SpaceArena(World world, String name, PartiesManager partiesManager, UsersManager<SpaceUser> usersManager, ClickedGuiManager guiManager, KitsManager kitsManager) {
		super(world, name, partiesManager);
		this.usersManager = usersManager;
		this.kitsManager = kitsManager;
		this.guiManager = guiManager;
		nexusLocations = new ArrayList<Location>();
	}

	@Override
	public void startGame() {
		super.startGame();
		selectRandomKitsIfNeeded();
		giveStartingItems();
		changeGameModes();
	}

	@Override
	public void setStatus(Status status) {
		super.setStatus(status);
	}
	
	@Override
	public boolean onPlayerJoin(Player player) {
		boolean onJoin = super.onPlayerJoin(player);
		if (onJoin) {
			openGuiMenu(player);
		}
		return onJoin;
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
	
	public void openGuiMenu(Player player) {
		GuiKit gui = guiManager.getGui(GuiKit.class);
		if (gui != null) {
			gui.open(player);
		}
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
	
	public void onShot(SpacePlayer shooter) {
		Gun gun = shooter.getKit().getGun();
		double maxDistance = gun.isLongBarrel() ? 75 : 50;
		Player player = shooter.getPlayer();
		Location playerPos = player.getEyeLocation();
		Location maxDistanceLocation = playerPos.clone().add(playerPos.getDirection().multiply(maxDistance));
		if (!gun.isFmj()) {
			Location collisionPoint = RayTrace.firstCollisionPoint(playerPos, maxDistanceLocation, new HashSet<Material>(), 0.05);
			if (collisionPoint != null) {
				maxDistanceLocation = collisionPoint;
				maxDistance = playerPos.distance(maxDistanceLocation);
			}
		}
		SpaceTeam team = shooter.getTeam();
		double minDistance = Double.MAX_VALUE;
		SpacePlayer targetFound = null;
		for (SpacePlayer target : players) {
			if (team != target.getTeam()) {
				Player targetPlayer = target.getPlayer();
				boolean isGliding = targetPlayer.isGliding();
				if (RayTrace.canHit(player, targetPlayer, maxDistance, isGliding ? 0.25 : 0, isGliding)) {
					double distance = playerPos.distance(targetPlayer.getEyeLocation());
					if (distance < minDistance) {
						targetFound = target;
						minDistance = distance;
					}
				}
			}
		}
		if (targetFound != null) {
			Player targetPlayer = targetFound.getPlayer();
			onDamage(targetFound, shooter, gun.getDamage());
			maxDistanceLocation = targetPlayer.isGliding() ? AABB.getElytraPlayerEyesPos(targetPlayer) : targetPlayer.getEyeLocation();
		}
		ParticlesHelper.particlesLineColored(playerPos, maxDistanceLocation, 0.1, shooter.getTeam().getArmorColor(), 1);
		world.playSound(player, gun.getSound(), 1, 32);
	}

	public void onDamage(SpacePlayer player, SpacePlayer damager, double damage) {
		onTrueDamage(player, damager, 100d / Math.max(1, 100 + player.getKit().getArmor()) * damage);
	}
	
	public void onTrueDamage(SpacePlayer player, SpacePlayer damager, double damage) {
		if (damager != null) {
			player.setLastAttacker(damager);
		}
		player.getPlayer().damage(damage);
	}
	
	public void onPlayerDeath(SpacePlayer spacePlayer) {
		spacePlayer.onDeath();
	}
	
	public static void heal(Player player) {
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}
	
	private void selectRandomKitsIfNeeded() {
		players.forEach(player -> {
			if (player.getKit() == null) {
				player.setKit(kitsManager.getRandomKit());
			}
		});
	}
	
	private void changeGameModes() {
		players.forEach(player -> player.setPlayingGameMode());
	}
	
	private void giveStartingItems() {
		teams.forEach(team -> team.getPlayers().forEach(spacePlayer -> {
			spacePlayer.giveDefaultItems();
		}));
	}
}
