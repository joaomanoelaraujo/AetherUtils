package me.lostedark.aetherplugins.utils.cmd;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("§cEste comando só pode ser executado por um jogador.");
         return true;
      } else {
         Player player = (Player)sender;
         if (args.length < 1) {
            player.sendMessage("§cUso correto: /gm <modo>");
            return true;
         } else {
            String var7 = args[0].toLowerCase();
            byte var8 = -1;
            switch(var7.hashCode()) {
            case -1684593425:
               if (var7.equals("spectator")) {
                  var8 = 6;
               }
               break;
            case -1600582850:
               if (var7.equals("survival")) {
                  var8 = 0;
               }
               break;
            case -694094064:
               if (var7.equals("adventure")) {
                  var8 = 4;
               }
               break;
            case 48:
               if (var7.equals("0")) {
                  var8 = 1;
               }
               break;
            case 49:
               if (var7.equals("1")) {
                  var8 = 3;
               }
               break;
            case 50:
               if (var7.equals("2")) {
                  var8 = 5;
               }
               break;
            case 51:
               if (var7.equals("3")) {
                  var8 = 7;
               }
               break;
            case 1820422063:
               if (var7.equals("creative")) {
                  var8 = 2;
               }
            }

            GameMode gameMode;
            switch(var8) {
            case 0:
            case 1:
               gameMode = GameMode.SURVIVAL;
               break;
            case 2:
            case 3:
               gameMode = GameMode.CREATIVE;
               break;
            case 4:
            case 5:
               gameMode = GameMode.ADVENTURE;
               break;
            case 6:
            case 7:
               gameMode = GameMode.SPECTATOR;
               break;
            default:
               player.sendMessage("§cModo inválido. Opções: survival, creative, adventure, spectator.");
               return true;
            }

            player.setGameMode(gameMode);
            player.sendMessage("§aSeu modo de jogo foi alterado para §c" + gameMode.name().toLowerCase() + "§a.");
            return true;
         }
      }
   }
}
