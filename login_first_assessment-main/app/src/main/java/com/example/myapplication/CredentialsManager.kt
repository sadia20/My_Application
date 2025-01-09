package com.example.myapplication

class CredentialsManager {
    private val accounts = mutableMapOf<String, String>()

    fun isValidEmail(email: String?): Boolean {
        if (email.isNullOrEmpty()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return emailRegex.matches(email)
    }

    fun isValidPassword(password: String?): Boolean {
        return !password.isNullOrEmpty()
    }

    fun register(email: String, password: String): String {
        return if (accounts.containsKey(email)) {
            "Error: Email already taken"
        } else {
            accounts[email] = password
            "Account created successfully"
        }
    }

    fun checkCredentials(email: String, password: String): Boolean {
        return (email == "test@te.st" && password == "1234") || (accounts[email] == password)
    }
}
