package it.tristana.spacewars.arena;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import it.tristana.spacewars.Main;
import it.tristana.spacewars.arena.kit.CombactClass;

public class ItemsBroker {

	private static final Material[] materials = new Material[] {
			Material.LEATHER_BOOTS,
			Material.LEATHER_LEGGINGS,
			null,
			Material.LEATHER_HELMET
	};
	
	private Main plugin;
	private SpaceArena arena;
	
	public ItemsBroker(Main plugin, SpaceArena arena) {
		this.plugin = plugin;
		this.arena = arena;
	}
	
	public void giveItems() {
		for (SpacePlayer player : arena.getPlayers()) {
			CombactClass kit = player.getKit();
			if (kit == null) {
				CombactClass[] kits = plugin.getKits();
				int index = (int) (Math.random() * kits.length);
				kit = kits[index];
				arena.onKitChosen(player, index);
			}
			PlayerInventory inventory = player.getPlayer().getInventory();
			inventory.clear();
			ItemStack[] armor = new ItemStack[materials.length];
			SpaceTeam team = player.getTeam();
			for (int i = 0; i < armor.length; i ++) {
				if (materials[i] != null) {
					armor[i] = getArmorPiece(materials[i], team);
				}
				else {
					armor[i] = plugin.getItems().getWings();
				}
			}
			inventory.setArmorContents(armor);
			inventory.addItem(kit.getClassItems());
		}
	}
	
	private ItemStack getArmorPiece(Material material, SpaceTeam team) {
		ItemStack armor = new ItemStack(material);
		LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.setColor(team.getColor());
		armor.setItemMeta(meta);
		return armor;
	}
}
