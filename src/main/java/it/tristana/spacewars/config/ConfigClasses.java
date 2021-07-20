package it.tristana.spacewars.config;

import java.io.File;
import java.util.Arrays;

import it.tristana.commons.config.Config;

public class ConfigClasses extends Config {
	
	public static final String CLASSES_EFFECT_COST = "Classes-effect-cost";
	
	private static final String GUN_INFO = "gun-info.";
	public static final String INFO_TITLE = GUN_INFO + "title";
	public static final String INFO_FIRE_RATIO = GUN_INFO + "fire-ratio";
	public static final String INFO_BASE_DAMAGE = GUN_INFO + "base-damage";
	public static final String INFO_FMJ = GUN_INFO + "fmj";
	public static final String INFO_LONG_BARREL = GUN_INFO + "long-barrel";
	
	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";
	private static final String DAMAGE = "damage";
	private static final String FIRE_RATIO = "fire-ratio";
	private static final String CLASS_EFFECT = "class-effect";

	private static final String MINER = "miner.";
	public static final String MINER_DESCRIPTION = MINER + DESCRIPTION;
	public static final String MINER_NAME = MINER + NAME;
	public static final String MINER_DAMAGE = MINER + DAMAGE;
	public static final String MINER_FIRE_RATIO = MINER + FIRE_RATIO;
	public static final String MINER_CLASS_EFFECT = MINER + CLASS_EFFECT;
	
	private static final String SNIPER = "sniper.";
	public static final String SNIPER_DESCRIPTION = SNIPER + DESCRIPTION;
	public static final String SNIPER_NAME = SNIPER + NAME;
	public static final String SNIPER_DAMAGE = SNIPER + DAMAGE;
	public static final String SNIPER_FIRE_RATIO = SNIPER + FIRE_RATIO;
	public static final String SNIPER_CLASS_EFFECT = SNIPER + CLASS_EFFECT;
	
	private static final String SOLDIER = "soldier.";
	public static final String SOLDIER_DESCRIPTION = SOLDIER + DESCRIPTION;
	public static final String SOLDIER_NAME = SOLDIER + NAME;
	public static final String SOLDIER_DAMAGE = SOLDIER + DAMAGE;
	public static final String SOLDIER_FIRE_RATIO = SOLDIER + FIRE_RATIO;
	public static final String SOLDIER_CLASS_EFFECT = SOLDIER + CLASS_EFFECT;
	
	private static final String STORMTROOPER = "stormtrooper.";
	public static final String STORMTROOPER_DESCRIPTION = STORMTROOPER + DESCRIPTION;
	public static final String STORMTROOPER_NAME = STORMTROOPER + NAME;
	public static final String STORMTROOPER_DAMAGE = STORMTROOPER + DAMAGE;
	public static final String STORMTROOPER_FIRE_RATIO = STORMTROOPER + FIRE_RATIO;
	public static final String STORMTROOPER_CLASS_EFFECT = STORMTROOPER + CLASS_EFFECT;
	
	private static final String DEFENDER = "defender.";
	public static final String DEFENDER_DESCRIPTION = DEFENDER + DESCRIPTION;
	public static final String DEFENDER_NAME = DEFENDER + NAME;
	public static final String DEFENDER_DAMAGE = DEFENDER + DAMAGE;
	public static final String DEFENDER_FIRE_RATIO = DEFENDER + FIRE_RATIO;
	public static final String DEFENDER_CLASS_EFFECT = DEFENDER + CLASS_EFFECT;
	
	private static final String TANK = "tank.";
	public static final String TANK_DESCRIPTION = TANK + DESCRIPTION;
	public static final String TANK_NAME = TANK + NAME;
	public static final String TANK_DAMAGE = TANK + DAMAGE;
	public static final String TANK_FIRE_RATIO = TANK + FIRE_RATIO;
	public static final String TANK_CLASS_EFFECT = TANK + CLASS_EFFECT;
	
	private static final String DESTROYER = "destroyer.";
	public static final String DESTROYER_DESCRIPTION = DESTROYER + DESCRIPTION;
	public static final String DESTROYER_NAME = DESTROYER + NAME;
	public static final String DESTROYER_DAMAGE = DESTROYER + DAMAGE;
	public static final String DESTROYER_FIRE_RATIO = DESTROYER + FIRE_RATIO;
	public static final String DESTROYER_CLASS_EFFECT = DESTROYER + CLASS_EFFECT;
	
	private static final String PYROMANIAC = "pyromaniac.";
	public static final String PYROMANIAC_DESCRIPTION = PYROMANIAC + DESCRIPTION;
	public static final String PYROMANIAC_NAME = PYROMANIAC + NAME;
	public static final String PYROMANIAC_DAMAGE = PYROMANIAC + DAMAGE;
	public static final String PYROMANIAC_FIRE_RATIO = PYROMANIAC + FIRE_RATIO;
	public static final String PYROMANIAC_CLASS_EFFECT = PYROMANIAC + CLASS_EFFECT;
	
	private static final String TRACEUR = "traceur.";
	public static final String TRACEUR_DESCRIPTION = TRACEUR + DESCRIPTION;
	public static final String TRACEUR_NAME = TRACEUR + NAME;
	public static final String TRACEUR_DAMAGE = TRACEUR + DAMAGE;
	public static final String TRACEUR_FIRE_RATIO = TRACEUR + FIRE_RATIO;
	public static final String TRACEUR_CLASS_EFFECT = TRACEUR + CLASS_EFFECT;
	
	public ConfigClasses(File folder) {
		super(new File(folder, "classes.yml"));
	}

	@Override
	protected void createDefault() {
		set(CLASSES_EFFECT_COST, "1500");
		
		set(MINER_DESCRIPTION, Arrays.asList(
				"&e&oClass equipped with a weak weapon. His main objective",
				"&e&ois to take advantage of his pickaxe enchanted with Efficiency",
				"&e&oIII to quickly destroy enemies' nexus in the early",
				"&e&ophases of the game"
		));
		set(MINER_NAME, "&aWalther P99");
		set(MINER_DAMAGE, "3.75");
		set(MINER_FIRE_RATIO, "1.2");
		set(MINER_CLASS_EFFECT, "Haste");

		set(SNIPER_DESCRIPTION, Arrays.asList(
				"&e&oThis class's weapon has a low fire ratio and a high",
				"&e&opotential to inflict heavy damage. As class's effect",
				"&e&oit cames with the long barrel powerup always active"
		));
		set(SNIPER_NAME, "&6McMillan TAC-50");
		set(SNIPER_DAMAGE, "10");
		set(SNIPER_FIRE_RATIO, "0.55");
		set(SNIPER_CLASS_EFFECT, "Viewfinder");

		set(SOLDIER_DESCRIPTION, Arrays.asList(
				"&e&oIts weapon is a gun capable of inflict moderate",
				"&e&odamage at a high fire ratio. It has a high chance",
				"&e&oto hit its target shooting a pink of bullets against it"
		));
		set(SOLDIER_NAME, "&9AK-47");
		set(SOLDIER_DAMAGE, "4.5");
		set(SOLDIER_FIRE_RATIO, "2");
		set(SOLDIER_CLASS_EFFECT, "Resistance");

		set(STORMTROOPER_DESCRIPTION, Arrays.asList(
				"&e&oIts main objective is to distract enemies while",
				"&e&othe miner breaks the enemies' nexus. Its weapon",
				"&e&ohas weak stats but it's upgraded with FMJ. When",
				"&e&othe game begins stormtroopers get an EMP and",
				"&e&othree firework rockets to start ahead"
		));
		set(STORMTROOPER_NAME, "&5M4A1 + FMJ");
		set(STORMTROOPER_DAMAGE, "3.75");
		set(STORMTROOPER_FIRE_RATIO, "1.4");
		set(STORMTROOPER_CLASS_EFFECT, "Regeneration");

		set(DEFENDER_DESCRIPTION, Arrays.asList(
				"&e&oHe's the defending member of the team in the early",
				"&e&ophases of the game. While he's in a radius of 32",
				"&e&oblocks from his nexus, his stats are increased:",
				"&e&o+25% fire ratio, +25% damage, +15% damage resistance"
		));
		set(DEFENDER_NAME, "&aGatlin Gun");
		set(DEFENDER_DAMAGE, "3.75");
		set(DEFENDER_FIRE_RATIO, "1.75");
		set(DEFENDER_CLASS_EFFECT, "Strenght");

		set(TANK_DESCRIPTION, Arrays.asList(
				"&e&oThe tank deals low damages, but has a really high",
				"&e&odamage reduction (35%). He's debuffed with Slowness",
				"&e&o2 (only affecting walk), but it can be removed",
				"&e&oby purchasing the class upgrade"
		));
		set(TANK_NAME, "&8Viper 5");
		set(TANK_DAMAGE, "2.75");
		set(TANK_FIRE_RATIO, "1.7");
		set(TANK_CLASS_EFFECT, "NoSlow");

		set(DESTROYER_DESCRIPTION, Arrays.asList(
				"&e&oThe destroyer is the assassin of the skies: he deals",
				"&e&oa huge amount of damage with a low fire ratio.",
				"&e&oHe has a problem though: he takes 35% more damage, so",
				"&e&odestroyers should be really good at aiming. His class",
				"&e&oupgrade gives him four consumable homing missiles"
		));
		set(DESTROYER_NAME, "&2Boomer");
		set(DESTROYER_DAMAGE, "14");
		set(DESTROYER_FIRE_RATIO, "0.3");
		set(DESTROYER_CLASS_EFFECT, "Missiles");

		set(PYROMANIAC_DESCRIPTION, Arrays.asList(
				"&e&oThe pyromaniac's weapon burns the evils! When an",
				"&e&oenemy is hit by him, the enemy gets a stack of flame.",
				"&e&oOnce the enemy reaches 3 stacks, he will start to burn.",
				"&e&oFlame stacks expire after a short delay, but hitting the",
				"&e&oenemy again refreshes the stacks duration"
		));
		set(PYROMANIAC_NAME, "&6The Purifier");
		set(PYROMANIAC_DAMAGE, "1.9");
		set(PYROMANIAC_FIRE_RATIO, "3");
		set(PYROMANIAC_CLASS_EFFECT, "Soul Fire");

		set(TRACEUR_DESCRIPTION, Arrays.asList(
				"&e&oThe traceur is the path finder member of the team. Due to the",
				"&e&ointrepid nature of traceurs, its weapon deals more damage when",
				"&e&ohe's flying, increasing up to 300% damage when flying at high",
				"&e&ospeeds. As class' upgrade he enpowers its capabilities,",
				"&e&obecoming able to dodge enemies' shots with a 25% chance"
		));
		set(TRACEUR_NAME, "&6Hawk");
		set(TRACEUR_DAMAGE, "2");
		set(TRACEUR_FIRE_RATIO, "1.4");
		set(TRACEUR_CLASS_EFFECT, "Dodge");

		set(INFO_TITLE, "&e&oInformation about class's gun");
		set(INFO_FIRE_RATIO, "&b&oFire ratio: &6&o{fire ratio} &b&orounds per second");
		set(INFO_BASE_DAMAGE, "&b&oBase damage: &6&o{base damage}");
		set(INFO_FMJ, "&b&oFMJ: {has FMJ}");
		set(INFO_LONG_BARREL, "&b&oLong barrel: {has long barrel}");
	}
}
