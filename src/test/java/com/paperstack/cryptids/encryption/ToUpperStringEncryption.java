package com.paperstack.cryptids.encryption;

import com.paperstack.cryptids.Encryption;
import org.springframework.stereotype.Component;

/**
 * A dumb "encryption" mechanism demonstrating that a custom encryption tool will receive and transform incoming
 * strings. Here we just convert them to upper case and append any value supplied in the {@link Encryption}
 * annotation for the property's class.
 */
@Component
public class ToUpperStringEncryption implements Encryption<String> {
    @Override
    public String encrypt(final String plaintext, final String annotationValue) {
        return String.format("%s/%s", plaintext.toUpperCase(), annotationValue == null ? "-" : annotationValue);
    }
}
