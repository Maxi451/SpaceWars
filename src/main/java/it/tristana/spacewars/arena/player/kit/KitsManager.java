package it.tristana.spacewars.arena.player.kit;

import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.helper.RegisterManager;
import it.tristana.spacewars.config.SettingsKits;

public class KitsManager extends RegisterManager<Kit> {

	private final SettingsKits settings;

	public KitsManager(PluginDraft plugin, SettingsKits settings) {
		super(plugin);
		this.settings = settings;
	}

	public void loadDefaultKits() {
		try {
			register(KitMiner.class);
			register(KitSoldier.class);
			register(KitSniper.class);
			register(KitDefender.class);
		} catch (NoSuchMethodException e) {
			throw new AssertionError("Missing a constructor with a single parameter of type " + SettingsKits.class.getName());
		}
	}

	public Kit[] getKitsView() {
		Kit[] kitsView = new Kit[entries.size()];
		for (int i = 0; i < kitsView.length; i ++) {
			kitsView[i] = getInstance(entries.get(i).clazz);
		}
		return kitsView;
	}

	@Override
	protected Object[] getConstructorArgs() {
		return new Object[] { settings };
	}

	@Override
	protected Class<?>[] getConstructorParameters() {
		return new Class[] { SettingsKits.class };
	}
}
