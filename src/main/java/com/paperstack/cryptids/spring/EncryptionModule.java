package com.paperstack.cryptids.spring;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

/**
 * A Jackson Module component; when included in the Spring application context
 * this will register the {@link StringEncryptionDeserializerModifier} with the
 * default ObjectMapper which will apply the encrpytion to appropriate fields.
 */
@Component
public class EncryptionModule extends SimpleModule {
    private final StringEncryptionDeserializerModifier modifier;

    private EncryptionModule(final StringEncryptionDeserializerModifier modifier) {
        super("EncryptionModule", new Version(1, 0, 0, null));
        this.modifier = modifier;
    }

    @Override
    public void setupModule(final SetupContext context) {
        context.addBeanDeserializerModifier(this.modifier);
    }
}