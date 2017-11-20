package ccd.rest.validation;

import java.util.regex.Pattern;

import ccd.exception.InputValidationException;

public final class Validator {


    private Validator() {}


    public static final String EMAIL = "" +
            "^[^ @]+@" +
            "[-|0-9|a-z|A-Z|\u0080-\uffff]+" +
            "(?:\\.[-|0-9|a-z|A-Z|\u0080-\uffff]+)+$";

    public static final String UUID = "" +
            "[0-9a-f]{8}-" +
            "[0-9a-f]{4}-" +
            "[345][0-9a-f]{3}-" + // accept UUID versions 3/5 (hash+namespace) and 4 (random)
            "[89ABab][0-9a-f]{3}-" + // first byte (the variant) is restricted by specification
            "[0-9a-f]{12}";

    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
    public static final Pattern UUID_PATTERN = Pattern.compile(UUID);


    public static void validateUUID(String uuId) {
        if (uuId == null) {
            throw new InputValidationException("uuId must not be null");
        }

        if (!UUID_PATTERN.matcher(uuId).matches()) {
            throw new InputValidationException("id pattern does not match the canonical representation of a UUIDv{3-5}");
        }
    }

//    public static boolean hasValidUUID(String uuId) {
//        try{
//            validateUUID(uuId);
//        } catch (RuntimeException e) {
//            return false;
//        }
//        return true;
//    }

    public static void validateEmail(String email) {
        if (email == null) {
            throw new InputValidationException("uuId must not be null");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InputValidationException("Email does not match to email pattern");
        }
    }

//    public static boolean hasValidEmail(String email) {
//        try{
//            validateEmail(email);
//        } catch (RuntimeException e) {
//            return false;
//        }
//        return true;
//    }
}
