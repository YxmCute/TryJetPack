package com.lij.tryjetpack

import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.lij.tryjetpack.lifecycle.LocationHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mLocationHelper: LocationHelper<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLocationHelper = LocationHelper(this, this.lifecycle, { isSuccess, location ->
            if (isSuccess) {
                location
            }
        }, {})
        lifecycle.addObserver(mLocationHelper)
        mLocationHelper.enable()
        mLocationHelper.setListener({}, {})
        val cc = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nv, cc)

        /*
        //https://stackoverflow.com/questions/50577356/android-jetpack-navigation-bottomnavigationview-with-youtube-or-instagram-like
        val cc = findNavController(R.id.nav_host_fragment)
         NavigationUI.setupWithNavController(bottom_nv, cc)*/


    }

}









