package vn.oleksandr.sandul.privatexchangerates.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import vn.oleksandr.sandul.privatexchangerates.R
import vn.oleksandr.sandul.privatexchangerates.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_container, MainFragment())
                .commit()
    }
}
