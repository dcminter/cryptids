package com.paperstack.cryptids.model;

import com.paperstack.cryptids.annotation.Plaintext;

/**
 * A simple payload with two fields to be encrypted and one to be left unencrypted
 */
public class SimplePayload {
    private final String socialSecurityNumber;
    private final String duperSecret;
    private final String notSoSecret;

    public SimplePayload(final String socialSecurityNumber, final String duperSecret, final String notSoSecret) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.duperSecret = duperSecret;
        this.notSoSecret = notSoSecret;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getDuperSecret() {
        return duperSecret;
    }

    @Plaintext
    public String getNotSoSecret() {
        return notSoSecret;
    }

    public String toString() {
        return String.format("%s:%s:%s", socialSecurityNumber, duperSecret, notSoSecret);
    }
}
