package it.tristana.spacewars.arena;

import org.bukkit.Color;

import it.tristana.commons.arena.player.BasicTeam;

public class SpaceTeam extends BasicTeam<SpacePlayer> {

	private Nexus nexus;
	private Color color;
	
	public SpaceTeam(String name, Color color, String colorCode) {
		super(name, colorCode);
		this.color = color;
	}
	
	public Nexus getNexus() {
		return nexus;
	}
	
	public void setNexus(Nexus nexus) {
		this.nexus = nexus;
	}
	
	public Color getColor() {
		return color;
	}
}
