package me.lostedark.aetherplugins.utils.bungee.commands.collections;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.lostedark.aetherplugins.utils.bungee.Bungee;
import me.lostedark.aetherplugins.utils.bungee.commands.Commands;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GoCommand extends Commands {
   public GoCommand() {
      super("go");
   }

   public void executeCommand(CommandSender sender, String[] args) {
      if (!(sender instanceof ProxiedPlayer)) {
         sender.sendMessage(TextComponent.fromLegacyText("§cEste comando é exclusivo apenas para jogadores!"));
      } else {
         ProxiedPlayer player = (ProxiedPlayer)sender;
         if (args.length < 1) {
            player.sendMessage(TextComponent.fromLegacyText(Bungee.getInstance().getConfig("config").getString("help")));
         } else {
            ProxiedPlayer targetPlayer = Bungee.getInstance().getProxy().getPlayer(args[0]);
            if (targetPlayer == null) {
               player.sendMessage(TextComponent.fromLegacyText(Bungee.getInstance().getConfig("config").getString("offPlayer")));
            } else {
               if (player.getServer().getInfo() != targetPlayer.getServer().getInfo()) {
                  player.connect(targetPlayer.getServer().getInfo());
               }

               (new Thread(() -> {
                  try {
                     Thread.sleep(300L);
                  } catch (InterruptedException var3) {
                     var3.printStackTrace();
                  }

                  ByteArrayDataOutput output = ByteStreams.newDataOutput();
                  output.writeUTF("teleport");
                  output.writeUTF(player.getName());
                  output.writeUTF(targetPlayer.getName());
                  targetPlayer.getServer().getInfo().sendData("AetherUtils", output.toByteArray());
                  player.sendMessage(TextComponent.fromLegacyText(Bungee.getInstance().getConfig("config").getString("teleportSucess").replace("{target}", targetPlayer.getName())));
               })).start();
            }
         }
      }

   }
}
