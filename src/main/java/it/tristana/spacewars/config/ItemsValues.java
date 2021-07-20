package it.tristana.spacewars.config;

import java.util.List;

import it.tristana.spacewars.interfaces.Configurable;

public class ItemsValues implements Configurable {

	private ConfigItems configItems;
	
	private String elytraName;
	private List<String> elytraLore;
	
	private String pickaxeName;
	private List<String> pickaxeLore;
	
	private String fuelName;
	private List<String> fuelLore;

	private String empName;
	private List<String> empLore;

	private String missileName;
	private List<String> missileLore;
	
	public ItemsValues(ConfigItems configItems) {
		this.configItems = configItems;
	}
	
	@Override
	public void setup() {
		elytraName = configItems.getString(ConfigItems.ELYTRA_NAME);
		elytraLore = configItems.getList(ConfigItems.ELYTRA_LORE);
		
		pickaxeName = configItems.getString(ConfigItems.PICKAXE_NAME);
		pickaxeLore = configItems.getList(ConfigItems.PICKAXE_LORE);
		
		fuelName = configItems.getString(ConfigItems.FUEL_NAME);
		fuelLore = configItems.getList(ConfigItems.FUEL_LORE);

		empName = configItems.getString(ConfigItems.EMP_NAME);
		empLore = configItems.getList(ConfigItems.EMP_LORE);

		missileName = configItems.getString(ConfigItems.MISSILE_NAME);
		missileLore = configItems.getList(ConfigItems.MISSILE_LORE);
	}

	public String getElytraName() {
		return elytraName;
	}

	public List<String> getElytraLore() {
		return elytraLore;
	}

	public String getPickaxeName() {
		return pickaxeName;
	}

	public List<String> getPickaxeLore() {
		return pickaxeLore;
	}

	public String getFuelName() {
		return fuelName;
	}

	public List<String> getFuelLore() {
		return fuelLore;
	}

	public String getEmpName() {
		return empName;
	}

	public List<String> getEmpLore() {
		return empLore;
	}

	public String getMissileName() {
		return missileName;
	}

	public List<String> getMissileLore() {
		return missileLore;
	}
}
