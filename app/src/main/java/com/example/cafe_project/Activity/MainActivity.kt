package com.example.cafe_project.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.cafe_project.R
import com.example.cafe_project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val explorerFragment = ExplorerFragment()
    private val cartFragment = CartFragment()
    private val favoriteFragment = FavoriteFragment()
    private val ordersFragment = OrdersFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        loadFragment(explorerFragment)

        val bottomNav: BottomNavigationView = binding.bottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_explorer -> loadFragment(explorerFragment)
                R.id.nav_cart -> loadFragment(cartFragment)
//                R.id.nav_favorite -> loadFragment(favoriteFragment)
                R.id.nav_orders -> loadFragment(ordersFragment)
                R.id.nav_profile -> loadFragment(profileFragment)
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }
}
