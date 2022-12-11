package com.example.chi_hw_clean_architecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.chi_hw_clean_architecture.R
import com.example.chi_hw_clean_architecture.databinding.ActivityMainBinding
import com.example.chi_hw_clean_architecture.di.Injection
import com.example.chi_hw_clean_architecture.presentation.screens.list.MarvelListFragment
import com.example.chi_hw_clean_architecture.presentation.screens.list.MarvelListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        )[MarvelListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, MarvelListFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.fetch -> {
                CoroutineScope(Dispatchers.Default).launch { viewModel.fetchMarvels() }
                true
            }
            R.id.save -> {
                viewModel.addMarvels()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}