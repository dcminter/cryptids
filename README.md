# Cryptids - an intercepting encyption tool for Jackson in Spring Boot

Cryptids is a small and very simple library to modify Spring Boot so that for REST requests being parsed by Jackson (which
is very much the standard approach) any String fields in the requests will by default be encrypted.

This is intended more of a proof of concept of that approach to intercepting and encrypting fields during
deserialization of payloads via Jackson. The total amount of code involved is very small and could easily
be re-implemented for a more thorough bespoke encryption suite.

## Usage

Include the `com.paperstack.cryptids.spring.EncryptionModule` and 
`com.paperstack.cryptids.spring.StringEncryptionDeserializerModifier` in the packages to be component scanned, or
explicitly as beans your application context.

Create an implementation of `com.paperstack.cryptids.Encryption<String>` and include this
in your application context. Exactly how you encrypt the data (and in future decrypt it) is
up to you.

Optionally on your Jackson model classes you can:

* Include a `@com.paperstack.cryptids.annotation.Encrypted` annotation with information that is useful to your
encryption logic - any value included in this annotation will be supplied as the second paraemeter of
the `encrypt` method in the `com.paperstack.cryptids.Encryption` interface.
* Include a `@com.paperstack.cryptids.annotation.Plaintext` annotation on fields that you specifically do not want to
have encrypted.

## Limitations

The encryption will only be applied to String fields of your model classes. If you have for example, a customer
account number stored in an Integer type, no attempt will be made to encrypt this.

The encryption will be applied to all deserialization in the default Jackson ObjectMapper in the 
Spring application context. This may be undesirable - for example if you only want to encrypt inputs
received by your own controllers but not ones received by explicit Http client calls that your application 
makes to external services.

Other than a toy example in the integration test suite, no encryption mechanisms are provided. A
future iteration will likely demonstrate this library in conjunction with the AWS Key Management 
System (KMS) tooling.

## Blacklists vs Whitelists

The annotations provided in this class allow you only to encrypt everything with select exemptions
rather than to encrypt only select fields. This is intentional; this is the "whitelisting" approach
where the reverse is to "blacklist" the fields.

A blacklist requires the developer to make the right decision every time; it is dangerously easy to 
fail to realist that a field might require security and include it in the suite for encryption. Whereas
fields that have *explicitly* been chosen for Plaintext will at least have had the minimal attention 
required to make that decision and so annotate them.

That said, no approach is foolproof, this one is for example vulnerable to cut & paste errors where the annotation 
is included by mistake. Similarly one might include a "comments" field for a `@Plaintext` annotation without
anticipating that a respondent might naively include personal information in this field when filling in a form.

## Requirements and Versions

This library assumes that it's running in a Spring Boot environment and has a dependency on version
**3.5.3** of Spring Boot. As such it also requires **Java 17** to build and run and the Maven Central artifacts
are therefore also built with Java 17 (via Coretto).