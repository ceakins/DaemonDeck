package io.github.ceakins.gamedaemondeck.plugins;

import java.io.IOException;
import java.nio.file.Path;

public class VeinPlugin implements GamePlugin {

    @Override
    public String getName() {
        return "Vein";
    }

    @Override
    public void startServer(Path serverPath) throws IOException {
        // Placeholder for starting the Vein server
    }

    @Override
    public void stopServer() throws IOException {
        // Placeholder for stopping the Vein server
    }

    @Override
    public String getBotName() {
        return null; // Or a specific bot name if applicable
    }
}
