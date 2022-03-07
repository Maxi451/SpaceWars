package it.tristana.spacewars.arena.player.kit;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.config.SettingsKits;

public class KitsManager {

	private Main plugin;
	private SettingsKits settings;
	private List<EntryKit<? extends Kit>> kits;
	
	public KitsManager(Main plugin, SettingsKits settings) {
		this.plugin = plugin;
		this.settings = settings;
		kits = new ArrayList<>();
	}
	
	public <K extends Kit> void registerKit(Class<K> clazz) throws NoSuchMethodException {
		for (EntryKit<?> entry : kits) {
			if (entry.clazz == clazz) {
				return;
			}
		}
		kits.add(new EntryKit<>(clazz));
	}
	
	public <K extends Kit> K getKit(Class<K> clazz) {
		Constructor<K> constructor = getConstructor(clazz);
		try {
			return constructor == null ? null : constructor.newInstance(settings);
		} catch (Exception e) {
			plugin.writeThrowableOnErrorsFile(e);
		}
		return null;
	}
	
	public Kit getRandomKit() {
		int size = kits.size();
		return size > 0 ? getKit(kits.get((int) (Math.random() * size)).clazz) : null;
	}
	
	public void loadDefaultKits() throws NoSuchMethodException {
		registerKit(KitMiner.class);
		registerKit(KitSoldier.class);
		registerKit(KitSniper.class);
		registerKit(KitDefender.class);
	}
	
	public Kit[] getKitsView() {
		Kit[] kitsView = new Kit[kits.size()];
		for (int i = 0; i < kitsView.length; i ++) {
			kitsView[i] = getKit(kits.get(i).clazz);
		}
		return kitsView;
	}
	
	@SuppressWarnings("unchecked")
	private <K> Constructor<K> getConstructor(Class<K> clazz) {
		for (EntryKit<?> entry : kits) {
			if (entry.clazz == clazz) {
				return (Constructor<K>) entry.constructor;
			}
		}
		return null;
	}
}
