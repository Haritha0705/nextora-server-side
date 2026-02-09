package lk.iit.nextora.config.fireBase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Firebase Configuration for Push Notifications.
 *
 * This configuration initializes Firebase Admin SDK for sending push notifications.
 * Firebase can be disabled via configuration when not needed.
 *
 * Configuration Properties:
 * - firebase.enabled: Enable/disable Firebase (default: false)
 * - firebase.config-path: Path to serviceAccountKey.json
 */
@Configuration
@Slf4j
public class FirebaseConfig {

    @Value("${firebase.config-path:}")
    private String firebaseConfigPath;

    @Value("${firebase.enabled:false}")
    private boolean firebaseEnabled;

    private final ResourceLoader resourceLoader;

    private boolean initialized = false;

    public FirebaseConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void initialize() {
        if (!firebaseEnabled) {
            log.info("Firebase is disabled via configuration. Push notifications will not be available.");
            return;
        }

        if (firebaseConfigPath == null || firebaseConfigPath.isBlank()) {
            log.warn("Firebase config path is not set. Push notifications will not work.");
            log.warn("Set firebase.config-path or FIREBASE_CONFIG_PATH environment variable.");
            return;
        }

        try {
            if (FirebaseApp.getApps().isEmpty()) {
                Resource resource = resourceLoader.getResource(firebaseConfigPath);

                if (!resource.exists()) {
                    log.warn("Firebase service account key not found at: {}. Push notifications will not work.", firebaseConfigPath);
                    log.warn("To enable push notifications, provide a valid serviceAccountKey.json file.");
                    return;
                }

                try (InputStream serviceAccount = resource.getInputStream()) {
                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();

                    FirebaseApp.initializeApp(options);
                    initialized = true;
                    log.info("Firebase Admin SDK initialized successfully");
                }
            } else {
                initialized = true;
                log.info("Firebase Admin SDK already initialized");
            }
        } catch (IOException e) {
            log.error("Failed to initialize Firebase Admin SDK: {}", e.getMessage());
            log.warn("Push notifications will not work until Firebase is properly configured.");
        }
    }

    /**
     * Create FirebaseMessaging bean as Optional to allow graceful degradation.
     * Returns null if Firebase is not initialized.
     */
    @Bean
    public Optional<FirebaseMessaging> firebaseMessaging() {
        if (!initialized) {
            log.debug("FirebaseMessaging bean requested but Firebase is not initialized - returning empty Optional");
            return Optional.empty();
        }
        return Optional.of(FirebaseMessaging.getInstance());
    }
}