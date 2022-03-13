package it.tristana.spacewars.arena.shop;

import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.helper.RegisterManager;
import it.tristana.spacewars.config.SettingsShop;

public class ItemsManager extends RegisterManager<SpaceItem> {

	private final SettingsShop settings;
	
	public ItemsManager(PluginDraft plugin, SettingsShop settings) {
		super(plugin);
		this.settings = settings;
	}
	
	public void loadDefaultItems() throws NoSuchMethodException {
		// register();
	}
	
	public SpaceItem[] getItemsView() {
		SpaceItem[] ItemsView = new SpaceItem[entries.size()];
		for (int i = 0; i < ItemsView.length; i ++) {
			ItemsView[i] = getInstance(entries.get(i).clazz);
		}
		return ItemsView;
	}

	@Override
	protected Object[] getConstructorArgs() {
		return new Object[] {settings};
	}

	@Override
	protected Class<?>[] getConstructorParameters() {
		return new Class[] {SettingsShop.class};
	}
}
