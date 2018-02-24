/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.explodingfood;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class FoodListener implements Listener {

    // Instance of the main class
    private ExplodingFood main;

    public FoodListener(ExplodingFood main) {
        // Passing the instance
        this.main = main;
    }

    // Event listener
    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        // Instance of the player
        final Player p = event.getPlayer();
        // Permission of avoiding the explosion, ops don't have by default, which I found to be an issue idk
        Permission avoidExplosion = new Permission("exploding-food.avoid-explosion", PermissionDefault.FALSE);
        // Check if the world isn't in disabled worlds (specified in the config)
        if (!main.disabledWorlds().contains(p.getWorld())) {
            // Checking if the player doesn't have the exploding-food.avoid-explosion permission which protects them from exploding
            if (!p.hasPermission(avoidExplosion)) {
                // Check if the food is contained inside the list specified in the config
                if (main.food().contains(event.getItem().getType())) {
                    // kaboom
                    p.getWorld().createExplosion(p.getLocation(), main.explosionPower());

                }
            }
        }
    }
}
