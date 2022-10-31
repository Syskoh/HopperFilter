package de.syskoh.hopperfilter.hopperfilter.events;

import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class HopperHandler implements Listener {


    // Hopper Pickup
    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent event){
        // Check if the item is a hopper
        if(event.getInventory().getType().equals(InventoryType.HOPPER)){
            // Is a hopper
            // Get item from nearby itemframe and check if it matches pickup item
            Location hopper = event.getInventory().getLocation();
            assert hopper != null;

            // Hopper exists
            hopper.getWorld().getNearbyEntities(hopper, 1.5,1.5,1.5).forEach(entity -> {
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
}

