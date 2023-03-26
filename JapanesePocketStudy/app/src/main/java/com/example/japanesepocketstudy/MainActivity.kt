package com.example.japanesepocketstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.japanesepocketstudy.database.Database
import com.example.japanesepocketstudy.databinding.ActivityMainBinding
import com.example.japanesepocketstudy.databinding.FragmentMainBinding
import com.example.japanesepocketstudy.entities.*
import com.example.japanesepocketstudy.ui.main.FragmentMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val db = Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "JapaneseDatabase"
        ).fallbackToDestructiveMigration().build()

        CoroutineScope(Dispatchers.IO).launch {
            if (db.kanjiDao().getAmount() > 0) {

            }
            if (db.sentenceDao().getAmount() > 0) {

            }
            if (db.dictionaryDao().getAmount() > 0) {

                println("FINISHED")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}