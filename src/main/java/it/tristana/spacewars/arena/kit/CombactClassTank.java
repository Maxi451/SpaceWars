package it.tristana.spacewars.arena.kit;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import it.tristana.spacewars.arena.SpacePlayer;
import it.tristana.spacewars.arena.combact.Gun;
import it.tristana.spacewars.arena.shop.ShopTank;

public class CombactClassTank extends CombactClass {

	public CombactClassTank() {
		super("Tank", new ShopTank(), Material.DIAMOND_CHESTPLATE, false);
	}

	@Override
	public List<String> getDescription() {
		return ccValues.getTankDescription();
	}
	
	@Override
	public double getDamageReduction(SpacePlayer arenaPlayer) {
		return 25;
	}
	
	@Override
	public void runStartActions(SpacePlayer spacePlayer) {
		spacePlayer.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 1, false, false, true));
	}

	@Override
	protected Gun createGun() {
		return new Gun(ccValues.getTankName(), Material.STONE_HOE, parseDouble(ccValues.getTankFireRatio(), 1.5), parseDouble(ccValues.getTankDamage(), 2.5), false, false);
	}
}
