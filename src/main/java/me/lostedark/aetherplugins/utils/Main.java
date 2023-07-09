package me.lostedark.aetherplugins.utils;

import me.lostedark.aetherplugins.utils.cmd.*;
import me.lostedark.aetherplugins.utils.listeners.*;
import me.lostedark.aetherplugins.utils.listeners.admin.BlazeRodEvent;
import me.lostedark.aetherplugins.utils.listeners.admin.CageEvent;
import me.lostedark.aetherplugins.utils.listeners.admin.NoFallEvent;
import me.lostedark.aetherplugins.utils.listeners.motd.MotdManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
   private static Main instance;
   private MotdManager motdManager;
   private ChatManager chatManager;
   private FileConfiguration config;
   private CageEvent cageEvent;
   public static Main getInstance() {
      return instance;
   }

   public void onEnable() {
      instance = this;
      config = getConfig();
      saveResource("cage.yml", false);
      this.saveDefaultConfig();
      this.chatManager = new ChatManager();
      String pluginName = this.getDescription().getName();
      String pluginVersion = this.getDescription().getVersion();
      this.getLogger().info("--------------------------------------");
      this.getLogger().info("|                                     |");
      this.getLogger().info("|   AETHER PLUGINS - BY D4RKK         |");
      this.getLogger().info("|   Plugin: " + pluginName + " v" + pluginVersion + "          |");
      this.getLogger().info("| Loja: https://plugins.aethershop.store |");
      this.getLogger().info("|                                     |");
      this.getLogger().info("| Obrigado por usar nossos plugins <3 |");
      this.getLogger().info("|                                     |");
      this.getLogger().info("--------------------------------------");
      this.getLogger().info("Plugin iniciado com sucesso!");
      this.getServer().getPluginManager().registerEvents(new ChatBlockListener(), this);
      this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
      ChatToggleCommand chatToggleCommand = new ChatToggleCommand();
      MotdReloadCommand motdReloadCommand = new MotdReloadCommand(this, this.motdManager);
      StaffChatCommand staffChatCommand = new StaffChatCommand(this);
      this.getCommand("motd").setExecutor(motdReloadCommand);
      this.getCommand("chat").setExecutor(chatToggleCommand);
      this.getCommand("v").setExecutor(new VanishCommand());
      this.getCommand("gm").setExecutor(new GamemodeCommand());
      this.getCommand("cc").setExecutor(new ClearChatCommand());
      this.getCommand("sc").setExecutor(staffChatCommand);
      getCommand("items").setExecutor(new ItemsCommand());
      this.motdManager = new MotdManager(this);
      this.motdManager.loadConfig();
      UtilsCommand utilsCommand = new UtilsCommand(this);
      this.getCommand("utils").setExecutor(utilsCommand);
      this.getCommand("maintenance").setExecutor(new MaintenanceCommand(this, this.motdManager));
      this.getServer().getPluginManager().registerEvents(this.motdManager, this);

      cageEvent = new CageEvent(getDataFolder());
      getServer().getPluginManager().registerEvents(cageEvent, this);
      getServer().getPluginManager().registerEvents(new NoFallEvent(), this);
      getServer().getPluginManager().registerEvents(new BlazeRodEvent(), this);
   }
   public FileConfiguration getCageConfig() {
      return config;
   }
   public void onDisable() {
      cageEvent.saveCageConfig();
      this.getLogger().info("Plugin desabilitado!");
   }
}
