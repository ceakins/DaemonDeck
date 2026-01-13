package io.github.ceakins.daemondeck.util;

import java.io.IOException;

public interface WebhookSender {
    void sendWebhookMessage(String url, String content) throws IOException;
}
