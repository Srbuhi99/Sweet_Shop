package com.example.sweetshop.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sweetshop.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val hostt: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navCont = hostt.navController
        setUpBottomNav(navCont)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.productDetailsFragment-> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }


    }


    fun setUpBottomNav(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.navigation_view)
        bottomNav?.setupWithNavController(navController)
        bottomNav.getOrCreateBadge(R.id.notication_tab).apply {
            number = 3
        }
    }



    private fun hideBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(navigation_view) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .alpha(0f)
                    .withEndAction { visibility = View.GONE }

            }
        }
    }

    private fun showBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(navigation_view) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
        }
    }
}
