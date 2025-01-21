package com.example.myapplication.signin

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.CoreFragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSigninBinding

class SignInFragment : CoreFragment<FragmentSigninBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate to Create Account screen
        binding?.registerNowTv?.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_createAccountFragment)
        }

        // Handle Sign-In button click
        binding?.signInLinear?.setOnClickListener {
            validateAndLogin()
        }
    }

    private fun validateAndLogin() {
        val email = binding?.inputTextSignIn?.text.toString().trim()
        val password = binding?.inputPasswordSignIn?.text.toString().trim()

        // Clear previous errors
        binding?.inputTextSignIn?.error = null
        binding?.inputPasswordSignIn?.error = null

        // Validation logic
        when {
            email.isEmpty() -> binding?.inputTextSignIn?.error = "Email cannot be empty"
            !isValidEmail(email) -> binding?.inputTextSignIn?.error = "Invalid email format"
            password.isEmpty() -> binding?.inputPasswordSignIn?.error = "Password cannot be empty"
            password.length < 4 -> binding?.inputPasswordSignIn?.error = "Password must be at least 4 characters"
            email == "test@te.st" && password == "1234" -> navigateToMain()
            else -> {
                showError("Invalid email or password")
            }
        }
    }

    private fun navigateToMain() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showError(message: String) {
        binding?.inputTextSignIn?.error = message
        binding?.inputPasswordSignIn?.error = message
    }
}
