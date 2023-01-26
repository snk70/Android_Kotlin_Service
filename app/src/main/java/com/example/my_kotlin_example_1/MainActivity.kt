package com.example.my_kotlin_example_1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.viewbinding.BuildConfig
import com.example.my_kotlin_example_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myStartBtn: Button = findViewById(R.id.btn_start)
        val myStopBtn: Button = findViewById(R.id.btn_stop)


        myStartBtn.setOnClickListener {
            MyService.startService(this, "Started Service...")
            val myToast = Toast.makeText(applicationContext, "Started!!!", Toast.LENGTH_LONG)
            myToast.show()
        }



        myStopBtn.setOnClickListener {
            MyService.stopService(this)
            val myToast = Toast.makeText(applicationContext, "Stoppedd!!", Toast.LENGTH_SHORT)
            myToast.show()
        }

    }


//    companion object {
//        const val ACTION_STOP_FOREGROUND = "${BuildConfig.LIBRARY_PACKAGE_NAME}.stopforeground"
//    }


}