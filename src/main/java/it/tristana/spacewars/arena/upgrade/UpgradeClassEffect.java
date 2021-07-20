package it.tristana.spacewars.arena.upgrade;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import it.tristana.spacewars.arena.SpacePlayer;

public abstract class UpgradeClassEffect extends Upgrade {

	protected final PotionEffectType potionEffectType;
	protected final boolean applyEffect;
	
	public UpgradeClassEffect(final ItemStack displayItem, final String name, final PotionEffectType potionEffectType, final boolean applyEffect) {
		super(displayItem, name, 1, parseIntOrGetDefault(combactClassesValues.getClassesEffectCost(), 1500), false);
		this.potionEffectType = potionEffectType;
		this.applyEffect = applyEffect;
	}
	
	@Override
	public final void doAction(final SpacePlayer arenaPlayer) {
		Player player = arenaPlayer.getPlayer();
		if (applyEffect) {
			player.addPotionEffect(new PotionEffect(potionEffectType, 999999, 0, false, false, true));
		}
		else {
			doSomethingElse(arenaPlayer);
		}
	}
	
	protected void doSomethingElse(final SpacePlayer arenaPlayer) {}
	
	public final boolean isUnlocked() {
		return currentLevel == 1;
	}
}