package me.lostedark.aetherplugins.utils.cmd;

import me.lostedark.aetherplugins.utils.listeners.motd.MotdManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MotdReloadCommand implements CommandExecutor {
   private final JavaPlugin plugin;
   private final MotdManager motdManager;

   public MotdReloadCommand(JavaPlugin plugin, MotdManager motdManager) {
      this.plugin = plugin;
      this.motdManager = motdManager;
   }

   public MotdReloadCommand(JavaPlugin plugin) {
      this.plugin = plugin;
      this.motdManager = null;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (command.getName().equalsIgnoreCase("motd") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
         if (this.motdManager != null) {
            this.motdManager.reloadConfig();
         }

         sender.sendMessage("§aMOTD recarregada com sucesso!");
         return true;
      } else {
         return false;
      }
   }
}
