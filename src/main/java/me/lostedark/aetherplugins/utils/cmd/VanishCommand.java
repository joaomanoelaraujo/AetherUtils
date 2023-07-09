package me.lostedark.aetherplugins.utils.cmd;

import me.lostedark.aetherplugins.utils.Main;
import me.lostedark.aetherplugins.utils.utils.ActionBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VanishCommand implements CommandExecutor {

   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("Este comando só pode ser executado por um jogador.");
         return true;
      }

      Player player = (Player) sender;
      if (!player.hasPermission("role.admin")) {
         player.sendMessage("§cVocê não tem permissão para usar este comando.");
         return true;
      }

      if (player.hasMetadata("vanished")) {
         for (Player otherPlayer : Bukkit.getServer().getOnlinePlayers()) {
            otherPlayer.showPlayer(player);
         }
         player.removeMetadata("vanished", Main.getInstance());
         player.sendMessage("§cModo Vanish desativado!");
         player.setDisplayName(player.getName()); // Restaurar o nome de exibição original
      } else {
         for (Player otherPlayer : Bukkit.getServer().getOnlinePlayers()) {
            otherPlayer.hidePlayer(player);
         }
         player.setMetadata("vanished", new FixedMetadataValue(Main.getInstance(), true));
         player.sendMessage("§aModo Vanish ativado!");
         player.setDisplayName(""); // Definir o nome de exibição como vazio
      }

      return true;
   }
}