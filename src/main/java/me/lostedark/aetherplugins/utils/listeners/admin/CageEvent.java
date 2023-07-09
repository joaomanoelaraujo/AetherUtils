package me.lostedark.aetherplugins.utils.listeners.admin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CageEvent implements Listener {

   private final File cageFile;
   private final YamlConfiguration cageConfig;

   public CageEvent(File dataFolder) {
      this.cageFile = new File(dataFolder, "cage.yml");
      this.cageConfig = YamlConfiguration.loadConfiguration(cageFile);
   }

   @EventHandler
   public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
      Player player = event.getPlayer();
      ItemStack item = player.getInventory().getItemInHand();

      if (item != null && item.getType() == Material.IRON_FENCE) {
         Player target = (Player) event.getRightClicked();
         createCage(target);
         player.sendMessage("Você colocou " + target.getName() + " em uma jaula de bedrock!");
      }
   }

   private void createCage(Player player) {
      ConfigurationSection cageSection = cageConfig.getConfigurationSection("cage");
      if (cageSection == null) {
         return; // A seção "cage" não existe no arquivo cage.yml
      }

      Location playerLocation = player.getLocation();
      List<String> blockList = cageSection.getStringList("blocks");

      for (String blockString : blockList) {
         String[] parts = blockString.split("; ");
         if (parts.length != 5) {
            continue; // Bloco inválido, pular para o próximo
         }

         double x = Double.parseDouble(parts[0]);
         double y = Double.parseDouble(parts[1]);
         double z = Double.parseDouble(parts[2]);
         Material material = Material.valueOf(parts[3]);
         int data = Integer.parseInt(parts[4]);

         Location blockLocation = playerLocation.clone().add(x, y, z);
         Block block = blockLocation.getBlock();
         block.setType(material);
         block.setData((byte) data);
      }
   }

   public void saveCageConfig() {
      try {
         cageConfig.save(cageFile);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}