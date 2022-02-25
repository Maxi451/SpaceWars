package it.tristana.spacewars.arena.player.kit;

import java.lang.reflect.Constructor;

import it.tristana.spacewars.config.SettingsKits;

class EntryKit<K extends Kit> {

	final Class<K> clazz;
	final Constructor<K> constructor;
	
	EntryKit(Class<K> clazz) throws NoSuchMethodException {
		this.clazz = clazz;
		constructor = clazz.getConstructor(SettingsKits.class);
	}
}
