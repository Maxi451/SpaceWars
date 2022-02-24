package it.tristana.spacewars.chat;

import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.chat.ChatManager;

public class SpaceChatManager implements ChatManager {

	@Override
	public void onChat(Player player, String message) {
		CommonsHelper.broadcast(player.getName() + " > " + message);
	}
}
