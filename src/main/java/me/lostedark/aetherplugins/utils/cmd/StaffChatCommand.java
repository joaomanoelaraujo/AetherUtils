package me.lostedark.aetherplugins.utils.cmd;

import java.util.Iterator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class StaffChatCommand implements CommandExecutor {
   private final Plugin plugin;
   private final String staffChatPrefix;

   public StaffChatCommand(Plugin plugin) {
      this.staffChatPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "StaffChat" + ChatColor.GRAY + "] " + ChatColor.RESET;
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("Este comando só pode ser executado por jogadores.");
         return true;
      } else {
         Player player = (Player)sender;
         if (!player.hasPermission("sc.admin")) {
            player.sendMessage(ChatColor.RED + "Você não tem permissão para utilizar o StaffChat.");
            return true;
         } else if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Uso incorreto do comando. Utilize /sc <mensagem> para enviar uma mensagem no StaffChat.");
            return true;
         } else {
            String message = String.join(" ", args);
            this.sendStaffChatMessage(player, message);
            return true;
         }
      }
   }

   private void sendStaffChatMessage(Player sender, String message) {
      Iterator var3 = this.plugin.getServer().getOnlinePlayers().iterator();

      while(var3.hasNext()) {
         Player player = (Player)var3.next();
         if (player.hasPermission("sc.admin")) {
            player.sendMessage(this.staffChatPrefix + sender.getDisplayName() + ": " + message);
         }
      }

   }
}
