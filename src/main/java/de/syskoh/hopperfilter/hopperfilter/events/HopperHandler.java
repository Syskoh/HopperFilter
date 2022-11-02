package de.syskoh.hopperfilter.hopperfilter.events;

import de.syskoh.hopperfilter.hopperfilter.Hopperfilter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Collection;

public class HopperHandler implements Listener {

    private static double radius = 1;

    // Hopper Pickup
    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent event){
        // Check if the item is a hopper
        if(event.getInventory().getType().equals(InventoryType.HOPPER)){
            // Is a hopper
            // Get item from nearby itemframe and check if it matches pickup item
            Location hopper = event.getInventory().getLocation();

            // Hopper exists

            Collection<Entity> entities = new ArrayList<>();
            entities.addAll(hopper.add(0,1,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(0,-2,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(1,1,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(-2,0,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(1,0,1).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(0,0,-2).getWorld().getNearbyEntities(hopper, radius, radius, radius));

            entities.forEach(entity -> {
                if(entity instanceof ItemFrame){

                    //Found ItemFrame, cancelling pickup
                    event.setCancelled(true);
                    ItemFrame itemFrame = (ItemFrame) entity;
                    if(event.getItem().getItemStack().equals(itemFrame.getItem())){


                        // Item matches, releasing item
                        event.setCancelled(false);
                        return;
                    }
                }
            });
        }
    }
    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event){
        // Check if the item is a hopper
        if(event.getDestination().getType().equals(InventoryType.HOPPER)){
            // Is a hopper
            // Get item from nearby itemframe and check if it matches pickup item
            Location hopper = event.getDestination().getLocation().add(0.5,0.5,0.5);
            Collection<Entity> entities = new ArrayList<>();
            entities.addAll(hopper.add(0,1,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(0,-2,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(1,1,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(-2,0,0).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(1,0,1).getWorld().getNearbyEntities(hopper, radius, radius, radius));
            entities.addAll(hopper.add(0,0,-2).getWorld().getNearbyEntities(hopper, radius, radius, radius));

            entities.forEach(entity -> {

                if(hopper.distance(entity.getLocation()) < 0.4 || hopper.distance(entity.getLocation()) > 1.0) {
                    return;
                }


                if(entity instanceof ItemFrame){
                    //Found ItemFrame, cancelling pickup
                    event.setCancelled(true);
                    ItemFrame itemFrame = (ItemFrame) entity;
                    if(event.getItem().equals(itemFrame.getItem())){

                        // Item matches, releasing item
                        event.setCancelled(false);
                        return;
                    }
                }
            });
        }
    }
}

