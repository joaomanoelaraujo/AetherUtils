package me.lostedark.aetherplugins.utils.bungee.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessageListeners implements PluginMessageListener {
   public void onPluginMessageReceived(String channel, Player sender, byte[] bytes) {
      if (channel.equalsIgnoreCase("AetherUtils")) {
         ByteArrayDataInput output = ByteStreams.newDataInput(bytes);
         String subChannel = output.readUTF();
         byte var7 = -1;
         switch(subChannel.hashCode()) {
         case -1360201941:
            if (subChannel.equals("teleport")) {
               var7 = 0;
            }
         default:
            switch(var7) {
            case 0:
               String playerName = output.readUTF();
               String targetName = output.readUTF();
               Player player = Bukkit.getPlayer(playerName);
               Player targetPlayer = Bukkit.getPlayer(targetName);
               player.teleport(targetPlayer);
            }
         }
      }

   }
}
