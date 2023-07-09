package me.lostedark.aetherplugins.utils.cmd;

import me.lostedark.aetherplugins.utils.listeners.ChatManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatToggleCommand implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender instanceof Player) {
         Player player = (Player)sender;
         if (player.hasPermission("role.admin")) {
            if (args.length == 1) {
               String subCommand = args[0].toLowerCase();
               if (subCommand.equals("on")) {
                  if (ChatManager.isChatBlocked()) {
                     ChatManager.setChatBlocked(false);
                     player.sendMessage("§aO chat foi ligado.");
                  } else {
                     player.sendMessage("§aO chat já está ligado.");
                  }
               } else if (subCommand.equals("off")) {
                  if (!ChatManager.isChatBlocked()) {
                     ChatManager.setChatBlocked(true);
                     player.sendMessage("§aO chat foi bloqueado.");
                  } else {
                     player.sendMessage("§cO chat já está bloqueado.");
                  }
               } else {
                  player.sendMessage("§cUso incorreto do comando. Utilize /chat <on|off>.");
               }
            } else {
               player.sendMessage("§cUso incorreto do comando. Utilize /chat <on|off>.");
            }
         } else {
            player.sendMessage("§cVocê não tem permissão para executar este comando.");
         }
      } else {
         sender.sendMessage("§cEste comando só pode ser executado por um jogador.");
      }

      return true;
   }
}
