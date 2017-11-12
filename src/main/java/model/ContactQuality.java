package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The <code>ContactQualityConstants</code> enum defines constants for valid
 * contact quality settings.
 */
@SuppressWarnings("PMD.SingularField")
public enum ContactQuality {

    SCHUFA_VALIDATED(1),
    ADDRESSCHECKER_VALIDATED(2),
    ADDRESS_NOT_DEFINIT(3),
    INCOMPLETE_ADDRESS(4),
    INVALID_ADDRESS(5),
    // Because of Bug number:  152786 Introduction  of a new contact-Quality Type
    ADDRESS_UNCHECKED(6),
    // Because of Project MaM.1600
    ADDRESS_MAY_BE_CORRECTED(7);

    private static final Map<Integer, ContactQuality> LOOKUP_MAP = new HashMap<Integer, ContactQuality>();

    static {
        for (final ContactQuality value : values()) {
            LOOKUP_MAP.put(value.getId(), value);
        }
    }

    private final Integer id;

    public Integer getId() {
        return this.id;
    }

    private ContactQuality(final Integer id) {
        this.id = id;
    }

    /**
     * Returns the {@link ContactQuality} whose id is equal to
     * the passed id.
     *
     * @param id null or non null
     * @return null or non null
     */
    public static ContactQuality getById(final Integer id) {
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
