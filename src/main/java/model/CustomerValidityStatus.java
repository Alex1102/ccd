package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The <code>CustomerValidityStatus</code> enum defines constants for
 * valid customer status.
 */
@SuppressWarnings("PMD.SingularField")
public enum CustomerValidityStatus {

    DELETED(0),
    ACTIVE(1),
    INACTIVE(2),
    INACTIVE_WITHOUT_EMAIL(3),
    DISUSED(4),
    PRIVACY_LOCK(5);

    private static final Map<Integer, CustomerValidityStatus> LOOKUP_MAP = new HashMap<Integer, CustomerValidityStatus>();

    static {
        for (final CustomerValidityStatus value : values()) {
            LOOKUP_MAP.put(value.getId(), value);
        }
    }

    private final Integer id;

    public Integer getId() {
        return this.id;
    }

    private CustomerValidityStatus(final Integer id) {
        this.id = id;
    }

    /**
     * Returns the {@link CustomerValidityStatus} whose id is equal to
     * the passed id.
     *
     * @param id null or non null
     * @return null or non null
     */
    public static CustomerValidityStatus getById(final Integer id) {
        if (!contains(id)) {
            throw new IllegalArgumentException("Could not map " + id + " to type.");
        }
        return LOOKUP_MAP.get(id);
    }


    public static CustomerValidityStatus getByIdOrNull(final Integer id) {
        final CustomerValidityStatus result;
        if (id == null) {
            result = ACTIVE;
        } else {
            result = getById(id);
        }

        return result;
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
