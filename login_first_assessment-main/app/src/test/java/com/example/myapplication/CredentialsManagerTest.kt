package com.example.myapplication

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()

    @Test
    fun testEmptyEmail() {
        assertFalse(credentialsManager.isValidEmail(""))
    }

    @Test
    fun testWrongFormatEmail() {
        assertFalse(credentialsManager.isValidEmail("invalid-email"))
        assertFalse(credentialsManager.isValidEmail("missing@domain"))
        assertFalse(credentialsManager.isValidEmail("missing.domain@"))
        assertFalse(credentialsManager.isValidEmail("invalid@domain@.com"))
    }

    @Test
    fun testWellFormattedEmail() {
        assertTrue(credentialsManager.isValidEmail("user@example.com"))
        assertTrue(credentialsManager.isValidEmail("name.surname@example.co.uk"))
    }

    @Test
    fun testEmptyPassword() {
        assertFalse(credentialsManager.isValidPassword(""))
    }

    @Test
    fun testFilledPassword() {
        assertTrue(credentialsManager.isValidPassword("securePassword123"))
    }

    @Test
    fun testRegisterNewAccount() {
        val result = credentialsManager.register("new.user@example.com", "newPassword123")
        assertEquals("Account created successfully", result)
    }

    @Test
    fun testRegisterExistingAccount() {
        credentialsManager.register("existing.user@example.com", "password123")
        val result = credentialsManager.register("existing.user@example.com", "newPassword123")
        assertEquals("Error: Email already taken", result)
    }

    @Test
    fun testHardcodedCredentials() {
        assertTrue(credentialsManager.checkCredentials("test@te.st", "1234"))
        assertFalse(credentialsManager.checkCredentials("test@te.st", "wrongPassword"))
    }
}
