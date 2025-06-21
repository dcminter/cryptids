package com.paperstack.cryptids.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paperstack.cryptids.annotation.Encrypted;
import com.paperstack.cryptids.annotation.Plaintext;

/**
 * A more complex model example showing that Jackson's properties are the ones
 * that matter for the placement of the {@link Plaintext} annotation - the
 * actual field, argument, or method names are not relevant.
 *
 * Also supplies an example of a value in the {@link Encrypted} annotation.
 */
@Encrypted("WOW")
public class TrickyDeserializationPayload {
    private final String f1;
    private final String f2;
    private final String f3;

    @JsonCreator
    public TrickyDeserializationPayload(@JsonProperty("socialSecurityNumber") final String foo, @JsonProperty("duperSecret") final String bar, @Plaintext final String notSoSecret) {
        this.f1 = foo;
        this.f2 = bar;
        this.f3 = notSoSecret;
    }

    public String getFoo() {
        return f1;
    }

    public String getBar() {
        return f2;
    }

    public String getPip() {
        return f3;
    }

    public String toString() {
        return String.format("%s:%s:%s", f1, f2, f3);
    }
}
