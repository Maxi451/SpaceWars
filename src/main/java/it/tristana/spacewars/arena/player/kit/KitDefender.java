package it.tristana.spacewars.arena.player.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.arena.player.gun.Gun;
import it.tristana.spacewars.arena.player.gun.GunDefender;
import it.tristana.spacewars.config.SettingsKits;

public class KitDefender extends Kit {

	public KitDefender(SettingsKits settings) {
		super(settings);
	}

	@Override
	public ItemStack getRawDisplayItem() {
		return new ItemStack(Material.SHIELD);
	}

	@Override
	public String getName() {
		return settings.getDefenderName();
	}

	@Override
	public List<String> getLore() {
		return settings.getDefenderLore();
	}

	@Override
	public double getBaseArmor() {
		return settings.getDefenderArmor();
	}
	
	@Override
	public double getArmor(SpacePlayer player) {
		double armor = super.getArmor(player);
		if (isNearNexus(player, settings)) {
			armor *= (1 + settings.getDefenderBonusArmorPercentage());
		}
		return armor;
	}

	@Override
	protected Gun forgeGun() {
		return new GunDefender(settings);
	}
	
	public static boolean isNearNexus(SpacePlayer player, SettingsKits settings) {
		return player.getPlayer().getLocation().distance(player.getTeam().getNexus().getLocation()) < settings.getDefenderMaxNexusDistance();
	}
}
