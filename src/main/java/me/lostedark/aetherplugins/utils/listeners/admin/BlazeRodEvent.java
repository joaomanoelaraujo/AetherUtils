package me.lostedark.aetherplugins.utils.listeners.admin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class BlazeRodEvent implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();
        ItemStack item = player.getInventory().getItemInHand();

        if (item != null && item.getType() == Material.BLAZE_ROD) {

            if (clickedEntity instanceof Player) {
                Player target = (Player) clickedEntity;
                Inventory targetInventory = target.getInventory();

                Inventory customInventory = Bukkit.createInventory((InventoryHolder) player, targetInventory.getSize(), "Inventário de " + target.getName());
                customInventory.setContents(targetInventory.getContents());

                player.openInventory(customInventory);
                player.sendMessage("Você abriu o inventário de " + target.getName());
            }
        }
    }
}