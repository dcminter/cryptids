package com.paperstack.cryptids.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Can be applied to a model type for extra visibility and/or to provide
 * an additional string parameter to calls to the {@link com.paperstack.cryptids.Encryption}
 * object.
 *
 * For example, in the follow example the value of the annotation is used to specify the
 * encryption algorithm to be used.
 * <pre>
 * {@code
 * @Encrypted("aes-256-cbc")
 * record User(String username, String name, String dateOfBirth) {}
 * }
 * </pre>
 *
 * Note - this value <b>MUST NOT</b> be used to pass encryption <i>keys</i> to the encryptiion object!
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypted {
    String value() default "";
}
