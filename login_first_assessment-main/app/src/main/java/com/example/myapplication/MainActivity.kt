package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerTextView = findViewById<TextView>(R.id.registerNow)
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        val emailInput: EditText = findViewById(R.id.email)
        val passwordInput: EditText = findViewById(R.id.password)
        val validateButton: Button = findViewById(R.id.nextButton)
        val emailInputLayout: TextInputLayout = findViewById(R.id.emailInputLayout)
        val passwordInputLayout: TextInputLayout = findViewById(R.id.passwordInputLayout)

        validateButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            emailInputLayout.error = null
            passwordInputLayout.error = null

            when {
                !credentialsManager.isValidEmail(email) -> emailInputLayout.error = "Invalid email format"
                !credentialsManager.isValidPassword(password) -> passwordInputLayout.error = "Password cannot be empty"
                credentialsManager.checkCredentials(email, password) -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                else -> passwordInputLayout.error = "Invalid credentials"
            }
        }
    }
}
