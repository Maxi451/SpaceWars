package it.tristana.spacewars.config;

import java.util.List;

import it.tristana.spacewars.interfaces.Configurable;

public class CombactClassesValues implements Configurable {

	private ConfigMessages configMessages;
	private ConfigClasses configClasses;
	
	private String yesWord;
	private String noWord;
	
	private String classesEffectCost;
	
	private String infoTitle;
	private String infoBaseDamage;
	private String infoFireRatio;
	private String infoFmj;
	private String infoLongBarrel;
	
	private List<String> minerDescription;
	private String minerName;
	private String minerDamage;
	private String minerFireRatio;
	private String minerClassEffect;
	
	private List<String> sniperDescription;
	private String sniperName;
	private String sniperDamage;
	private String sniperFireRatio;
	private String sniperClassEffect;
	
	private List<String> soldierDescription;
	private String soldierName;
	private String soldierDamage;
	private String soldierFireRatio;
	private String soldierClassEffect;
	
	private List<String> stormtrooperDescription;
	private String stormtrooperName;
	private String stormtrooperDamage;
	private String stormtrooperFireRatio;
	private String stormtrooperClassEffect;
	
	private List<String> defenderDescription;
	private String defenderName;
	private String defenderDamage;
	private String defenderFireRatio;
	private String defenderClassEffect;
	
	private List<String> tankDescription;
	private String tankName;
	private String tankDamage;
	private String tankFireRatio;
	private String tankClassEffect;
	
	private List<String> destroyerDescription;
	private String destroyerName;
	private String destroyerDamage;
	private String destroyerFireRatio;
	private String destroyerClassEffect;
	
	private List<String> pyromaniacDescription;
	private String pyromaniacName;
	private String pyromaniacDamage;
	private String pyromaniacFireRatio;
	private String pyromaniacClassEffect;
	
	private List<String> traceurDescription;
	private String traceurName;
	private String traceurDamage;
	private String traceurFireRatio;
	private String traceurClassEffect;
	
	public CombactClassesValues(ConfigMessages configMessages, ConfigClasses configClasses) {
		this.configMessages = configMessages;
		this.configClasses = configClasses;
	}
	
	@Override
	public void setup() {
		yesWord = configMessages.getString(ConfigMessages.YES_WORD);
		noWord = configMessages.getString(ConfigMessages.NO_WORD);
		
		classesEffectCost = configClasses.getString(ConfigClasses.CLASSES_EFFECT_COST);
		
		infoTitle = configClasses.getString(ConfigClasses.INFO_TITLE);
		infoBaseDamage = configClasses.getString(ConfigClasses.INFO_BASE_DAMAGE);
		infoFireRatio = configClasses.getString(ConfigClasses.INFO_FIRE_RATIO);
		infoFmj = configClasses.getString(ConfigClasses.INFO_FMJ);
		infoLongBarrel = configClasses.getString(ConfigClasses.INFO_LONG_BARREL);
		
		minerDescription = configClasses.getList(ConfigClasses.MINER_DESCRIPTION);
		minerName = configClasses.getString(ConfigClasses.MINER_NAME);
		minerDamage = configClasses.getString(ConfigClasses.MINER_DAMAGE);
		minerFireRatio = configClasses.getString(ConfigClasses.MINER_FIRE_RATIO);
		minerClassEffect = configClasses.getString(ConfigClasses.MINER_CLASS_EFFECT);
		
		sniperDescription = configClasses.getList(ConfigClasses.SNIPER_DESCRIPTION);
		sniperName = configClasses.getString(ConfigClasses.SNIPER_NAME);
		sniperDamage = configClasses.getString(ConfigClasses.SNIPER_DAMAGE);
		sniperFireRatio = configClasses.getString(ConfigClasses.SNIPER_FIRE_RATIO);
		sniperClassEffect = configClasses.getString(ConfigClasses.SNIPER_CLASS_EFFECT);
		
		soldierDescription = configClasses.getList(ConfigClasses.SOLDIER_DESCRIPTION);
		soldierName = configClasses.getString(ConfigClasses.SOLDIER_NAME);
		soldierDamage = configClasses.getString(ConfigClasses.SOLDIER_DAMAGE);
		soldierFireRatio = configClasses.getString(ConfigClasses.SOLDIER_FIRE_RATIO);
		soldierClassEffect = configClasses.getString(ConfigClasses.SOLDIER_CLASS_EFFECT);
		
		stormtrooperDescription = configClasses.getList(ConfigClasses.STORMTROOPER_DESCRIPTION);
		stormtrooperName = configClasses.getString(ConfigClasses.STORMTROOPER_NAME);
		stormtrooperDamage = configClasses.getString(ConfigClasses.STORMTROOPER_DAMAGE);
		stormtrooperFireRatio = configClasses.getString(ConfigClasses.STORMTROOPER_FIRE_RATIO);
		stormtrooperClassEffect = configClasses.getString(ConfigClasses.STORMTROOPER_CLASS_EFFECT);
		
		defenderDescription = configClasses.getList(ConfigClasses.DEFENDER_DESCRIPTION);
		defenderName = configClasses.getString(ConfigClasses.DEFENDER_NAME);
		defenderDamage = configClasses.getString(ConfigClasses.DEFENDER_DAMAGE);
		defenderFireRatio = configClasses.getString(ConfigClasses.DEFENDER_FIRE_RATIO);
		defenderClassEffect = configClasses.getString(ConfigClasses.DEFENDER_CLASS_EFFECT);
		
		tankDescription = configClasses.getList(ConfigClasses.TANK_DESCRIPTION);
		tankName = configClasses.getString(ConfigClasses.TANK_NAME);
		tankDamage = configClasses.getString(ConfigClasses.TANK_DAMAGE);
		tankFireRatio = configClasses.getString(ConfigClasses.TANK_FIRE_RATIO);
		tankClassEffect = configClasses.getString(ConfigClasses.TANK_CLASS_EFFECT);
		
		destroyerDescription = configClasses.getList(ConfigClasses.DESTROYER_DESCRIPTION);
		destroyerName = configClasses.getString(ConfigClasses.DESTROYER_NAME);
		destroyerDamage = configClasses.getString(ConfigClasses.DESTROYER_DAMAGE);
		destroyerFireRatio = configClasses.getString(ConfigClasses.DESTROYER_FIRE_RATIO);
		destroyerClassEffect = configClasses.getString(ConfigClasses.DESTROYER_CLASS_EFFECT);
		
		pyromaniacDescription = configClasses.getList(ConfigClasses.PYROMANIAC_DESCRIPTION);
		pyromaniacName = configClasses.getString(ConfigClasses.PYROMANIAC_NAME);
		pyromaniacDamage = configClasses.getString(ConfigClasses.PYROMANIAC_DAMAGE);
		pyromaniacFireRatio = configClasses.getString(ConfigClasses.PYROMANIAC_FIRE_RATIO);
		pyromaniacClassEffect = configClasses.getString(ConfigClasses.PYROMANIAC_CLASS_EFFECT);
		
		traceurDescription = configClasses.getList(ConfigClasses.TRACEUR_DESCRIPTION);
		traceurName = configClasses.getString(ConfigClasses.TRACEUR_NAME);
		traceurDamage = configClasses.getString(ConfigClasses.TRACEUR_DAMAGE);
		traceurFireRatio = configClasses.getString(ConfigClasses.TRACEUR_FIRE_RATIO);
		traceurClassEffect = configClasses.getString(ConfigClasses.TRACEUR_CLASS_EFFECT);
	}

	public String getYesWord() {
		return yesWord;
	}

	public String getNoWord() {
		return noWord;
	}

	public String getClassesEffectCost() {
		return classesEffectCost;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public String getInfoBaseDamage() {
		return infoBaseDamage;
	}
	
	public String getInfoFireRatio() {
		return infoFireRatio;
	}

	public String getInfoFmj() {
		return infoFmj;
	}

	public String getInfoLongBarrel() {
		return infoLongBarrel;
	}

	public List<String> getMinerDescription() {
		return minerDescription;
	}

	public String getMinerName() {
		return minerName;
	}

	public String getMinerDamage() {
		return minerDamage;
	}

	public String getMinerFireRatio() {
		return minerFireRatio;
	}

	public String getMinerClassEffect() {
		return minerClassEffect;
	}

	public List<String> getSniperDescription() {
		return sniperDescription;
	}

	public String getSniperName() {
		return sniperName;
	}

	public String getSniperDamage() {
		return sniperDamage;
	}

	public String getSniperFireRatio() {
		return sniperFireRatio;
	}

	public String getSniperClassEffect() {
		return sniperClassEffect;
	}

	public List<String> getSoldierDescription() {
		return soldierDescription;
	}

	public String getSoldierName() {
		return soldierName;
	}

	public String getSoldierDamage() {
		return soldierDamage;
	}

	public String getSoldierFireRatio() {
		return soldierFireRatio;
	}

	public String getSoldierClassEffect() {
		return soldierClassEffect;
	}

	public List<String> getStormtrooperDescription() {
		return stormtrooperDescription;
	}

	public String getStormtrooperName() {
		return stormtrooperName;
	}

	public String getStormtrooperDamage() {
		return stormtrooperDamage;
	}

	public String getStormtrooperFireRatio() {
		return stormtrooperFireRatio;
	}

	public String getStormtrooperClassEffect() {
		return stormtrooperClassEffect;
	}

	public List<String> getDefenderDescription() {
		return defenderDescription;
	}

	public String getDefenderName() {
		return defenderName;
	}

	public String getDefenderDamage() {
		return defenderDamage;
	}

	public String getDefenderFireRatio() {
		return defenderFireRatio;
	}

	public String getDefenderClassEffect() {
		return defenderClassEffect;
	}

	public List<String> getTankDescription() {
		return tankDescription;
	}

	public String getTankName() {
		return tankName;
	}

	public String getTankDamage() {
		return tankDamage;
	}

	public String getTankFireRatio() {
		return tankFireRatio;
	}

	public String getTankClassEffect() {
		return tankClassEffect;
	}

	public List<String> getDestroyerDescription() {
		return destroyerDescription;
	}

	public String getDestroyerName() {
		return destroyerName;
	}

	public String getDestroyerDamage() {
		return destroyerDamage;
	}

	public String getDestroyerFireRatio() {
		return destroyerFireRatio;
	}

	public String getDestroyerClassEffect() {
		return destroyerClassEffect;
	}

	public List<String> getPyromaniacDescription() {
		return pyromaniacDescription;
	}

	public String getPyromaniacName() {
		return pyromaniacName;
	}

	public String getPyromaniacDamage() {
		return pyromaniacDamage;
	}

	public String getPyromaniacFireRatio() {
		return pyromaniacFireRatio;
	}

	public String getPyromaniacClassEffect() {
		return pyromaniacClassEffect;
	}

	public List<String> getTraceurDescription() {
		return traceurDescription;
	}

	public String getTraceurName() {
		return traceurName;
	}

	public String getTraceurDamage() {
		return traceurDamage;
	}

	public String getTraceurFireRatio() {
		return traceurFireRatio;
	}

	public String getTraceurClassEffect() {
		return traceurClassEffect;
	}
}