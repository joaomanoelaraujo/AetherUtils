package me.lostedark.aetherplugins.utils.listeners.motd;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MotdManager implements Listener {
   private final JavaPlugin plugin;
   private FileConfiguration config;
   private File configFile;
   private boolean maintenanceMode;

   public MotdManager(JavaPlugin plugin) {
      this.plugin = plugin;
      this.configFile = new File(plugin.getDataFolder(), "motd.yml");
      this.config = YamlConfiguration.loadConfiguration(this.configFile);
      this.createDefaultConfig();
   }

   public void loadConfig() {
      if (!this.configFile.exists()) {
         this.plugin.saveResource("motd.yml", false);
      }

      this.config = YamlConfiguration.loadConfiguration(this.configFile);
   }

   public void saveConfig() {
      try {
         this.config.save(this.configFile);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void reloadConfig() {
      this.config = YamlConfiguration.loadConfiguration(this.configFile);
   }

   private void createDefaultConfig() {
      if (!this.configFile.exists()) {
         this.plugin.saveResource("motd.yml", false);
      }

   }

   public String getMotd() {
      String motd = this.config.getString("motd.default", "Bem-vindo ao Meu Servidor!");
      return ChatColor.translateAlternateColorCodes('&', motd.replace("\\n", "\n"));
   }

   public void setMotd(String motd) {
      motd = motd.replace("\n", "\\n");
      this.config.set("motd.default", motd);
      this.saveConfig();
   }

   public boolean isMaintenanceModeEnabled() {
      return this.maintenanceMode;
   }

   public void setMaintenanceMode(boolean enabled) {
      this.maintenanceMode = enabled;
   }

   @EventHandler
   public void onServerListPing(ServerListPingEvent event) {
      if (this.isMaintenanceModeEnabled()) {
         String maintenanceMotd = this.config.getString("motd.maintenance", "&cManutenção ativada");
         event.setMotd(ChatColor.translateAlternateColorCodes('&', maintenanceMotd));
      } else {
         event.setMotd(this.getMotd());
      }

   }
}
