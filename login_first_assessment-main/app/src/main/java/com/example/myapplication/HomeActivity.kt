package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class HomeActivity : AppCompatActivity() {

    private lateinit var toggleButton: Button
    private var isFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to activity_home.xml
        setContentView(R.layout.activity_home)

        // Initialize the button using lazy properties or findViewById
        toggleButton = findViewById(R.id.toggleButton)

        // Set initial fragment to FragmentA (optional if already set via XML)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, FragmentA())
                .commit()
        }

        // Set up button click listener to toggle fragments
        toggleButton.setOnClickListener {
            toggleFragment()
        }
    }

    private fun toggleFragment() {
        val newFragment: Fragment = if (isFragmentA) {
            FragmentB()
        } else {
            FragmentA()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, newFragment)
            .commit()

        isFragmentA = !isFragmentA
    }
}
