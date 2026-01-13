package io.github.ceakins.daemondeck.db;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.mvstore.MVStoreModule;
import org.dizitart.no2.repository.ObjectRepository;

import java.util.Optional;

public class ConfigStore {

    private static ConfigStore instance;
    private final Nitrite db;
    private final ObjectRepository<Configuration> configRepository;

    private ConfigStore() {
        MVStoreModule storeModule = MVStoreModule.withConfig()
                .filePath("daemondeck.db")
                .build();

        this.db = Nitrite.builder()
                .loadModule(storeModule)
                .openOrCreate();
        this.configRepository = db.getRepository(Configuration.class);
    }

    public static synchronized ConfigStore getInstance() {
        if (instance == null) {
            instance = new ConfigStore();
        }
        return instance;
    }

    public Optional<Configuration> getConfiguration() {
        return configRepository.find().toList().stream().findFirst();
    }

    public void saveConfiguration(Configuration config) {
        Optional<Configuration> existingConfig = getConfiguration();
        if (existingConfig.isPresent()) {
            config.setId(existingConfig.get().getId());
            configRepository.update(config);
        } else {
            configRepository.insert(config);
        }
    }

    public boolean isConfigured() {
        return configRepository.size() > 0;
    }

    public void close() {
        if (db != null && !db.isClosed()) {
            db.close();
        }
    }
}