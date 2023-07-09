package me.lostedark.aetherplugins.utils;

import me.lostedark.aetherplugins.utils.bungee.listeners.PluginMessageListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Bungee extends JavaPlugin {
   private static Bungee instance;

   public static Bungee getInstance() {
      return instance;
   }

   public void onLoad() {
      instance = this;
   }

   public void onEnable() {
      Bukkit.getMessenger().registerIncomingPluginChannel(this, "go", new PluginMessageListeners());
      this.sendMessage("O plugin inicou com sucesso!");
   }

   public void onDisable() {
      this.sendMessage("O plugin inicou com sucesso!");
   }

   public void sendMessage(String message) {
      Bukkit.getConsoleSender().sendMessage("§a[" + this.getDescription().getName() + "] " + message);
   }

   public void sendMessage(String message, Character color) {
      Bukkit.getConsoleSender().sendMessage("§" + color + "[" + this.getDescription().getName() + "] " + message);
   }
}
