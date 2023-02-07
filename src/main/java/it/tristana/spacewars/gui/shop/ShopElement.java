package it.tristana.spacewars.gui.shop;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import it.tristana.commons.gui.BasicElement;
import it.tristana.commons.interfaces.arena.ArenasManager;
import it.tristana.commons.interfaces.shop.ShopItem;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.config.SettingsShop;

public abstract class ShopElement extends BasicElement implements ShopItem<SpacePlayer> {

	protected final SettingsShop settings;
	protected final ArenasManager<SpaceArena, SpacePlayer> arenasManager;
	private final Supplier<String> nameGetter;

	public ShopElement(SettingsShop settings, ArenasManager<SpaceArena, SpacePlayer> arenasManager, Supplier<String> nameGetter, Supplier<List<String>> loreGetter) {
		super(nameGetter.get(), loreGetter.get());
		this.settings = settings;
		this.arenasManager = arenasManager;
		this.nameGetter = nameGetter;
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

		doAction(arena.getArenaPlayer(player));
	}

	@Override
	public final boolean doAction(SpacePlayer balanceHolder) {
		double price = getPrice();
		return balanceHolder.getMoney() > price && run(balanceHolder) && balanceHolder.tryToPay(price);
	}

	@Override
	public final String getName() {
		return nameGetter.get();
	}

	protected abstract boolean run(SpacePlayer spacePlayer);
}
