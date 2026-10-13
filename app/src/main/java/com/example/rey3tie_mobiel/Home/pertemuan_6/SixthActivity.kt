package com.example.rey3tie_mobiel.Home.pertemuan_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.rey3tie_mobiel.R
import com.example.rey3tie_mobiel.databinding.ActivitySixthBinding

class SixthActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySixthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pertemuan 6"


        replaceFragment(SatuFragment())


        binding.btnFragment1.setOnClickListener {
            replaceFragment(SatuFragment())
        }
        binding.btnFragment2.setOnClickListener {
            replaceFragment(DuaFragment())
        }
        binding.btnFragment3.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}