package base.domain;

import java.util.UUID;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class OidGenerator {
	public static String createId() {
		UUID uuid = java.util.UUID.randomUUID();
		return uuid.toString();
	}
}
