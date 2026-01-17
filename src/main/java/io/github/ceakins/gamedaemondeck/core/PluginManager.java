package io.github.ceakins.gamedaemondeck.core;

import io.github.ceakins.gamedaemondeck.plugins.GamePlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class PluginManager {

    private final List<GamePlugin> plugins;

    public PluginManager() {
        this.plugins = new ArrayList<>();
        ServiceLoader<GamePlugin> loader = ServiceLoader.load(GamePlugin.class);
        for (GamePlugin plugin : loader) {
            plugins.add(plugin);
        }
    }

    public List<GamePlugin> getPlugins() {
        return plugins;
    }

    public GamePlugin getPlugin(String name) {
        return plugins.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
