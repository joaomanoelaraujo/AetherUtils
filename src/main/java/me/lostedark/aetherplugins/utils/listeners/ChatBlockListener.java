package me.lostedark.aetherplugins.utils.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatBlockListener implements Listener {
   @EventHandler
   public void onPlayerChat(PlayerChatEvent event) {
      if (ChatManager.isChatBlocked() && !event.getPlayer().hasPermission("role.admin")) {
         event.setCancelled(true);
         event.getPlayer().sendMessage("§cO chat está bloqueado tente novamente quando um administrador ligar.");
      }

   }
}
