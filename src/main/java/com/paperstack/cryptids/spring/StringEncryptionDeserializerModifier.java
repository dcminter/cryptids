package com.paperstack.cryptids.spring;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.paperstack.cryptids.annotation.Encrypted;
import com.paperstack.cryptids.annotation.Plaintext;
import com.paperstack.cryptids.Encryption;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * A Bean Deserializer Modifier that when included in the application context with the {@link EncryptionModule} applies
 * encryption to String fields being deserialized by Jackson (unless the target property is annotated
 * as {@link Plaintext})
 */
@Component
public class StringEncryptionDeserializerModifier extends BeanDeserializerModifier {
    private final Encryption<String> encryption;

    public StringEncryptionDeserializerModifier(final Encryption<String> encryption) {
        this.encryption = encryption;
    }

    public JsonDeserializer<?> modifyDeserializer(final DeserializationConfig config,
                                                  final BeanDescription description,
                                                  final JsonDeserializer<?> deserializer) {
        if( StringDeserializer.class.isAssignableFrom(deserializer.getClass())) {
            return new EncryptingDeserializer();
        } else {
            return deserializer;
        }
    }

    private class EncryptingDeserializer extends StringDeserializer implements ContextualDeserializer {
        private final StringDeserializer delegate = new StringDeserializer();
        @Override
        public JsonDeserializer<?> createContextual(final DeserializationContext context, final BeanProperty property) throws JsonMappingException {
            if(null == property.getAnnotation(Plaintext.class)) {
                // Encrypted property, so build and return the encrypting serializer
                final Encrypted contextAnnotation = property.getContextAnnotation(Encrypted.class);
                return new StringDeserializer() {
                    public String deserialize(JsonParser p, DeserializationContext context) throws IOException {
                        return encryption.encrypt(delegate.deserialize(p, context), contextAnnotation == null ? null : contextAnnotation.value());
                    }
                };
            } else {
                // Plaintext property, so return the unadulterated serializer
                return delegate;
            }
        }
    }
}
