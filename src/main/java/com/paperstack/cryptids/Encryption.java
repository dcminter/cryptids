package com.paperstack.cryptids;

/**
 * The interface that will be invoked for encryption of each String in the JSON being deserialized into
 * the Jackson model.
 *
 * @param <T> The type of the field to be encrypted - note that only String fields are currently supported.
 */
public interface Encryption<T> {
    /**
     * The method to be called to encrypt each String before it is used to populate the actual model properties
     *
     * @param plaintext The unencrypted value of the property (to be encrypted)
     * @param annotationValue The value of the {@link com.paperstack.cryptids.annotation.Encrypted} annotation in the context of the property (can be an empty string or null)
     * @return The encrypted value
     */
    T encrypt(T plaintext, String annotationValue);
}
