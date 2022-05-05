package com.example.nwaster

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nwaster.databinding.BottomNavBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class BottomNavActivity : AppCompatActivity() {


    private lateinit var binding: BottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_warehouse, R.id.navigation_lists, R.id.navigation_diet
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    //MENÚ DE TRES PUNTOS PARA TEMA DE PERFIL DE USUARIO
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.user_profile_menu -> {
                Toast.makeText(this, "Abrir actividad ajustes perfil de usuario", Toast.LENGTH_SHORT).show()
                // Action goes here
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}