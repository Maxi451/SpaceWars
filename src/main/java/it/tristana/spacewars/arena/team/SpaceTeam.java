package it.tristana.spacewars.arena.team;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import it.tristana.commons.arena.player.BasicTeam;
import it.tristana.commons.interfaces.Tickable;
import it.tristana.spacewars.arena.SpaceArena;
import it.tristana.spacewars.arena.player.ElementsCounter;
import it.tristana.spacewars.arena.player.SpacePlayer;
import it.tristana.spacewars.gui.shop.ShopElement;
import it.tristana.spacewars.helper.ItemUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class SpaceTeam extends BasicTeam<SpacePlayer, SpaceArena> implements Tickable {

	private Nexus nexus;
	private Color armorColor;
	private ChatColor chatColor;
	private int lives;
	
	private ElementsCounter<ShopElement> itemsManager;

	public SpaceTeam(SpaceArena arena, Location spawnpoint, String name, String colorCode, Nexus nexus, Color armorColor) {
		super(arena, spawnpoint, name, colorCode, armorColor);
		this.nexus = nexus;
		nexus.setTeam(this);
		this.armorColor = armorColor;
		this.chatColor = ChatColor.of(new java.awt.Color(armorColor.asRGB()));
		this.itemsManager = new ElementsCounter<>();
	}

	@Override
	public void runTick() {
		nexus.runTick();
	}
	
	public void buyItem(Class<? extends ShopElement> clazz) {
		itemsManager.addElement(clazz);
	}
	
	public int getItemLevel(Class<? extends ShopElement> clazz) {
		return itemsManager.getAmount(clazz);
	}

	public void removeLife() {
		lives --;
	}

	public void addLife() {
		lives ++;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLives() {
		return lives;
	}

	public Nexus getNexus() {
		return nexus;
	}

	public ItemStack[] getArmor() {
		return new ItemStack[] {
				createArmor(Material.LEATHER_BOOTS),
				createArmor(Material.LEATHER_LEGGINGS),
				ItemUtils.forgeUnbreakableItem(new ItemStack(Material.ELYTRA)),
				createArmor(Material.LEATHER_HELMET)
		};
	}

	public TextComponent toColoredTextComponent(String message) {
		TextComponent text = new TextComponent(message);
		text.setColor(chatColor);
		return text;
	}

	public Color getArmorColor() {
		return armorColor;
	}

	public boolean hasPlayers() {
		return !players.isEmpty();
	}

	private ItemStack createArmor(Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		if (!(meta instanceof LeatherArmorMeta)) {
			return item;
		}
		LeatherArmorMeta leather = (LeatherArmorMeta) meta;
		leather.setColor(armorColor);
		item.setItemMeta(leather);
		return item;
	}
}
