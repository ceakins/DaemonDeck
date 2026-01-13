package io.github.ceakins.daemondeck.core;

import io.github.ceakins.daemondeck.db.ConfigStore;
import io.github.ceakins.daemondeck.db.Configuration;
import io.javalin.Javalin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DaemonDeckAppTest {

    @Mock
    private ConfigStore configStore;

    @Mock
    private DiscordService discordService;

    private DaemonDeckApp daemonDeckApp;
    private Javalin app;
    private final OkHttpClient client = new OkHttpClient.Builder().followRedirects(false).build();

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        daemonDeckApp = new DaemonDeckApp(configStore, discordService);
        app = daemonDeckApp.app;
        app.start(0); // Start on a random available port
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    @Test
    public void testSetupPageServedIfNotConfigured() throws IOException {
        when(configStore.isConfigured()).thenReturn(false);

        Request request = new Request.Builder()
                .url("http://localhost:" + app.port() + "/setup")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(response.code(), 200);
            assertTrue(response.body().string().contains("DaemonDeck First-Run Setup"));
        }
    }

    @Test
    public void testRootRedirectsToSetupIfNotConfigured() throws IOException {
        when(configStore.isConfigured()).thenReturn(false);

        Request request = new Request.Builder()
                .url("http://localhost:" + app.port() + "/")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(response.code(), 302);
            assertTrue(response.header("Location").contains("/setup"));
        }
    }

    @Test
    public void testSetupPostConfiguresAppAndRedirects() throws IOException {
        when(configStore.isConfigured()).thenReturn(false);

        okhttp3.FormBody body = new okhttp3.FormBody.Builder()
                .add("adminUsername", "testuser")
                .add("adminPassword", "testpass")
                .add("steamCmdPath", "/path/to/steamcmd")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:" + app.port() + "/setup")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(response.code(), 302);
            assertTrue(response.header("Location").contains("/"));
            verify(configStore).saveConfiguration(any(Configuration.class));
        }
    }

    @Test
    public void testRootRequiresAuthenticationWhenConfigured() throws IOException {
        Configuration config = new Configuration();
        config.setAdminUsername("admin");
        config.setAdminPasswordHash(BCrypt.hashpw("password", BCrypt.gensalt()));
        when(configStore.isConfigured()).thenReturn(true);
        when(configStore.getConfiguration()).thenReturn(Optional.of(config));

        Request request = new Request.Builder()
                .url("http://localhost:" + app.port() + "/")
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(response.code(), 401);
            assertTrue(response.header("WWW-Authenticate").contains("Basic"));
        }
    }
}