package com.paperstack.cryptids.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Can be applied to a property of a model type in order to disable the encryption
 * of that particular property. This annotation should only be used on properties
 * that are not confidential.
 *
 * For example:
 * <pre>
 * {@code
 * @Encrypted("aes-256-cbc")
 * record User(String username, String name, @Plaintext String publicStatus) {}
 * }
 * </pre>
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Plaintext {
}