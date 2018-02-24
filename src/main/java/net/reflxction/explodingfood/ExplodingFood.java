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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * An easy to use, very configurable plugin which adds an explode-upon-consume function to the server
 */
public final class ExplodingFood extends JavaPlugin {

    // Plugin startup logic
    @Override
    public void onEnable() {
        // Save the config (we're using #saveResource so it saves comments in the config
        saveResource("config.yml", false);
        // Register the listener
        getServer().getPluginManager().registerEvents(new FoodListener(this), this);
    }


    /**
     * @return List of the disabled worlds
     */
    public List<World> disabledWorlds() {
        List<World> worlds = new ArrayList<>();
        List<String> worldsString = getConfig().getStringList("DisabledWorlds");
        worldsString.forEach(s -> worlds.add(Bukkit.getWorld(s)));
        return worlds;
    }

    /**
     * @return List of the food that can bomb you
     */
    public List<Material> food() {
        List<Material> food = new ArrayList<>();
        List<String> foodString = getConfig().getStringList("Food");

        try {
            foodString.forEach(s -> food.add(Material.getMaterial(s.toUpperCase())));
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "The specified material in the config is invalid. Please ensure that it's spelled correctly.");
        }
        System.out.println(food);
        return food;
    }

    /**
     * @return Explosion power specified in the config
     */
    public int explosionPower() {
        return getConfig().getInt("ExplosionPower");
    }

}
