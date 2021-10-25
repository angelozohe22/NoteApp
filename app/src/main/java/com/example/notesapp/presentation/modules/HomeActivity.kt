package com.example.notesapp.presentation.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.notesapp.R
import com.example.notesapp.data.interactor.NotesInteractor
import com.example.notesapp.data.source.local.LocalNotesSource
import com.example.notesapp.data.source.local.db.NotesDatabase
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.presentation.viewmodel.NoteViewModel
import com.example.notesapp.presentation.viewmodel.NoteViewModelFactory
import com.google.android.material.appbar.AppBarLayout

class HomeActivity : AppCompatActivity() {

    //binding
    private lateinit var binding : ActivityMainBinding

    private lateinit var appBarLayout   : AppBarLayout
    private lateinit var toolbar        : Toolbar
    private lateinit var navController  : NavController

    //viewModel
    val viewModel by viewModels<NoteViewModel> {
        NoteViewModelFactory(
            NotesInteractor(
                local = LocalNotesSource(NotesDatabase.getInstance(this))
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBinding()
        setupViews()
    }

    private fun setupViews() {
        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.fragment_container)
        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
    }


    private fun initBinding() {
        appBarLayout  = binding.appBarLayout
        toolbar       = binding.toolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}