package me.lostedark.aetherplugins.utils.listeners.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class NoFallEvent implements Listener {

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if (item != null && item.getType() == Material.FEATHER) {

            if (event.getRightClicked() instanceof Player) {
                Player target = (Player) event.getRightClicked();

                Vector upVector = new Vector(0, 15, 0);
                target.setVelocity(upVector);

                player.sendMessage("Você jogou " + target.getName() + " para cima!");
            }
        }
    }
}