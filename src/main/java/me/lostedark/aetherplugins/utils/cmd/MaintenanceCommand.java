package me.lostedark.aetherplugins.utils.cmd;

import me.lostedark.aetherplugins.utils.listeners.motd.MotdManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MaintenanceCommand implements CommandExecutor {
   private final JavaPlugin plugin;
   private final MotdManager motdManager;

   public MaintenanceCommand(JavaPlugin plugin, MotdManager motdManager) {
      this.motdManager = motdManager;
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("Este comando só pode ser executado por um jogador.");
         return true;
      } else {
         Player player = (Player)sender;
         if (!player.hasPermission("role.admin")) {
            player.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando.");
            return true;
         } else {
            if (args.length >= 1) {
               String subCommand = args[0].toLowerCase();
               if (subCommand.equals("on")) {
                  if (!this.motdManager.isMaintenanceModeEnabled()) {
                     this.motdManager.setMaintenanceMode(true);
                     player.sendMessage(ChatColor.GREEN + "Modo de manutenção ativado. O MOTD foi atualizado.");
                     this.plugin.getServer().setWhitelist(true);
                  } else {
                     player.sendMessage(ChatColor.YELLOW + "O modo de manutenção já está ativado.");
                  }
               } else if (subCommand.equals("off")) {
                  if (this.motdManager.isMaintenanceModeEnabled()) {
                     this.motdManager.setMaintenanceMode(false);
                     this.plugin.getServer().setWhitelist(false);
                     player.sendMessage(ChatColor.GREEN + "Modo de manutenção desativado. O MOTD foi atualizado.");
                  } else {
                     player.sendMessage(ChatColor.YELLOW + "O modo de manutenção já está desativado.");
                  }
               } else {
                  String playerName;
                  if (subCommand.equals("add")) {
                     if (args.length >= 2) {
                        playerName = args[1];
                        this.plugin.getServer().getOfflinePlayer(playerName).setWhitelisted(true);
                        player.sendMessage(ChatColor.GREEN + "O jogador " + playerName + " foi adicionado à whitelist.");
                     } else {
                        player.sendMessage(ChatColor.RED + "Uso incorreto do comando. Utilize /maintenance add <jogador>.");
                     }
                  } else if (subCommand.equals("remove")) {
                     if (args.length >= 2) {
                        playerName = args[1];
                        this.plugin.getServer().getOfflinePlayer(playerName).setWhitelisted(false);
                        player.sendMessage(ChatColor.GREEN + "O jogador " + playerName + " foi removido da whitelist.");
                     } else {
                        player.sendMessage(ChatColor.RED + "Uso incorreto do comando. Utilize /maintenance remove <jogador>.");
                     }
                  } else {
                     player.sendMessage(ChatColor.RED + "Uso incorreto do comando. Utilize /maintenance <on|off|add|remove> <jogador>.");
                  }
               }
            } else {
               player.sendMessage(ChatColor.RED + "Uso incorreto do comando. Utilize /maintenance <on|off|add|remove> <jogador>.");
            }

            return true;
         }
      }
   }
}
