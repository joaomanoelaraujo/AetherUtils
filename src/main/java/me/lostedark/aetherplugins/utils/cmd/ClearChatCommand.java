package me.lostedark.aetherplugins.utils.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cEste comando só pode ser executado por um jogador.");
         return true;
      } else {
         Player player = (Player)sender;
         if (!player.hasPermission("role.admin")) {
            player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
            return true;
         } else {
            if (command.getName().equalsIgnoreCase("cc")) {
               this.clearChat(player);
            } else if (command.getName().equalsIgnoreCase("clearchat")) {
               this.clearChat(player);
            }

            player.sendMessage(ChatColor.GREEN + "O chat foi limpo.");
            return true;
         }
      }
   }

   private void clearChat(Player player) {
      for(int i = 0; i < 100; ++i) {
         player.sendMessage("");
      }

   }
}
