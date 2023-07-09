package me.lostedark.aetherplugins.utils.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class UtilsCommand implements CommandExecutor {
   private final Plugin plugin;

   public UtilsCommand(Plugin plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (sender.hasPermission("role.admin")) {
         if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            this.plugin.getServer().getPluginManager().disablePlugin(this.plugin);
            this.plugin.getServer().getPluginManager().enablePlugin(this.plugin);
            this.plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Plugin recarregado com sucesso.");
            return true;
         }

         sender.sendMessage(ChatColor.RED + "Uso incorreto do comando. Utilize /utils reload.");
      } else {
         sender.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando.");
      }

      return false;
   }
}
