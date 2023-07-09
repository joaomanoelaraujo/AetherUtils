package me.lostedark.aetherplugins.utils.bungee;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Objects;
import me.lostedark.aetherplugins.utils.bungee.commands.Commands;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

public class Bungee extends Plugin {
   private static Bungee instance;

   public static Bungee getInstance() {
      return instance;
   }

   public void onLoad() {
      instance = this;
      this.loadConfiguration("config");
   }

   public void onEnable() {
      this.getProxy().registerChannel("AetherUtils");
      Commands.setupCommands();
      this.sendMessage("O plugin iniciou com sucesso!");
   }

   public void loadConfiguration(String... filesName) {
      String[] var2 = filesName;
      int var3 = filesName.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String fileName = var2[var4];
         File file = new File("plugins/" + this.getDescription().getName() + "/" + fileName + ".yml");
         if (!file.exists()) {
            File folder = file.getParentFile();
            if (!folder.exists()) {
               folder.mkdirs();
            }

            try {
               Files.copy((InputStream)Objects.requireNonNull(getInstance().getClass().getResourceAsStream("/" + fileName + ".yml")), file.toPath(), new CopyOption[0]);
            } catch (IOException var9) {
               var9.printStackTrace();
            }
         }
      }

   }

   public Configuration getConfig(String configName) {
      try {
         return YamlConfiguration.getProvider(YamlConfiguration.class).load(new File("plugins/" + this.getDescription().getName() + "/" + configName + ".yml"));
      } catch (IOException var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public void onDisable() {
      this.sendMessage("O plugin desligou com sucesso!");
   }

   public void sendMessage(String message) {
      this.getLogger().info("§a" + message);
   }

   public void sendMessage(String message, Character color) {
      this.getLogger().info("§" + color + message);
   }
}
