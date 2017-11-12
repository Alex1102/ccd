package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The <code>CommunicationType</code> enum defines constants for valid
 * communication types.
 */
@SuppressWarnings("PMD.SingularField")
public enum CommunicationType {

    PHONE(1),
    FAX(2),
    MOBILE(3),
    EMAIL(4),
    HOMEPAGE(5),
    ADDITIONAL_PHONE(6),
    PAYPAL_EMAIL(7),
    DEMAIL_EMAIL(8);

    private static final Map<Integer, CommunicationType> LOOKUP_MAP = new HashMap<Integer, CommunicationType>();

    static {
        for (final CommunicationType value : values()) {
            LOOKUP_MAP.put(value.getId(), value);
        }
    }

    private final Integer id;

    public Integer getId() {
        return this.id;
    }

    private CommunicationType(final Integer id) {
        this.id = id;
    }

    /**
     * Returns the {@link CommunicationType} whose id is equal to
     * the passed id.
     *
     * @param id null or non null
     * @return null or non null
     */
    public static CommunicationType getById(final Integer id) {
        if (!contains(id)) {
            throw new IllegalArgumentException("Could not map " + id + " to type.");
        }
        return LOOKUP_MAP.get(id);
    }

    /**
     * Provides a simple check to ensure that a Id is valid in the context of this enum
     *
     * @param id ... to check
     * @return true if id is valid in the context of this enum
     */
    public static boolean contains(final Integer id) {
        return LOOKUP_MAP.containsKey(id);
    }
}
