package com.example.myapplication.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.CoreFragment
import com.example.myapplication.CredentialsManager
import com.example.myapplication.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : CoreFragment<FragmentCreateAccountBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate back to Sign In screen
        binding?.backToSignIn?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Handle Create Account button click
        binding?.createAccountLinear?.setOnClickListener {
            handleAccountCreation()
        }
    }

    private fun handleAccountCreation() {
        val email = binding?.inputTextCA2?.text.toString().trim()
        val password = binding?.passwordTextEditText?.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please fill in all fields.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(
                requireContext(),
                "Please enter a valid email address.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val registrationSuccess = CredentialsManager.register(email, password)

        if (registrationSuccess) {
            Toast.makeText(
                requireContext(),
                "Registration successful: $email",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                requireContext(),
                "Registration failed: Email already exists",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
