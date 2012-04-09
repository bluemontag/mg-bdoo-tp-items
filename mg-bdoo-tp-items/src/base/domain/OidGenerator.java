package base.domain;

import java.util.UUID;

public class OidGenerator {
    public static String createId() {
        UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
}
