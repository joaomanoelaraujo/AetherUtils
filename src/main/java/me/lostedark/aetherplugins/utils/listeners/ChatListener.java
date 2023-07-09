package me.lostedark.aetherplugins.utils.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
   @EventHandler
   public void onPlayerChat(AsyncPlayerChatEvent event) {
      String message = event.getMessage();
      if (message.equals(message.toUpperCase())) {
         event.setMessage(message.toLowerCase());
      }

   }
}
