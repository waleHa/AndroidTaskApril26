package com.example.androidtaskapril25

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.androidtaskapril25.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Replace the container view with the SignInFragment
                if(savedInstanceState == null){
                    supportFragmentManager.commit {
                        replace(binding.fragmentContainer.id, SignInFragment())
                    }
                }
    }
}
