package it.tristana.spacewars.gui.shop;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import it.tristana.commons.gui.BasicElement;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.shop.ShopItem;
import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsMessages;
import it.tristana.spacewars.config.SettingsShop;

public abstract class ShopElement extends BasicElement implements ShopItem<SpacePlayer> {

	private static final Main plugin = JavaPlugin.getPlugin(Main.class);

	protected final SettingsShop settingsShop;
	protected final SettingsMessages settingsMessages;
	protected final ArenasManager<SpaceArena, SpacePlayer> arenasManager;

	public ShopElement(String name, List<String> lore) {
		super(name, lore);
		this.settingsShop = plugin.getSettingsShop();
		this.settingsMessages = plugin.getSettingsMessages();
		this.arenasManager = plugin.getArenasManager();
	}

	@Override
	public final boolean closesInventory(Player player) {
		return false;
	}

	@Override
	public final void onClick(Player player) {
		SpaceArena arena = arenasManager.getArenaWithPlayer(player);
		if (arena == null) {
			return;
		}

		SpacePlayer spacePlayer = arena.getArenaPlayer(player);
		int maxLevel = getMaxLevel();
		Class<? extends ShopElement> clazz = getClass();
		boolean isTeamUpgrade = isTeamUpgrade();
		if (maxLevel >= 0) {
			int level = spacePlayer.getItemLevel(clazz, isTeamUpgrade);
			if (level >= maxLevel) {
				CommonsHelper.info(player, CommonsHelper.replaceAll(settingsMessages.getMaxItemLevelReached(), "{item}", name));
				player.closeInventory();
				return;
			}
		}

		int price = (int) getPrice();
		if (!spacePlayer.tryToPay(price)) {
			CommonsHelper.info(player, CommonsHelper.replaceAll(settingsMessages.getNotEnoughMoney(), "{money}", String.valueOf(price - (int) spacePlayer.getMoney())));
			player.closeInventory();
			return;
		}

		doAction(spacePlayer);
		spacePlayer.buyItem(clazz, isTeamUpgrade);
		CommonsHelper.info(player, CommonsHelper.replaceAll(settingsMessages.getItemBought(), "{item}", name));
	}

	@Override
	public boolean doAction(SpacePlayer balanceHolder) {
		return true;
	}

	@Override
	public final String getName() {
		return name;
	}
	
	public boolean isTeamUpgrade() {
		return false;
	}

	protected abstract void run(SpacePlayer spacePlayer);

	protected abstract int getMaxLevel();
}
