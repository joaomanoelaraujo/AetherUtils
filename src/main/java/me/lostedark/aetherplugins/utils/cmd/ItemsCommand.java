package me.lostedark.aetherplugins.utils.cmd;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ItemsCommand implements CommandExecutor {

   @Override
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage("Este comando só pode ser executado por um jogador.");
         return true;
      }

      Player player = (Player) sender;
      if (!player.hasPermission("role.admin")) {
         player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
         return true;
      }

      PlayerInventory inventory = player.getInventory();


      inventory.addItem(createCustomItem(Material.BLAZE_ROD, "§aVisualizar Inventário"));
      inventory.addItem(createCustomItem(Material.FEATHER, "§aNo Fall"));

      player.sendMessage(ChatColor.GREEN + "Você recebeu os itens!");

      return true;
   }

   private ItemStack createCustomItem(Material material, String name) {
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      meta.setDisplayName(ChatColor.RESET + name);
      item.setItemMeta(meta);
      return item;
   }
}