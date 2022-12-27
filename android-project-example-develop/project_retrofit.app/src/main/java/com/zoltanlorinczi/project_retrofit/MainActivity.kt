package com.zoltanlorinczi.project_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retorfit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called!")
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavBar.setSelectedItemId(R.id.listFragment);

        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.activities -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.usersListFragment)
                }
                R.id.mytasks -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.listFragment)
                }
                R.id.mygroups -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.groupListFragment)
                }
                R.id.profile -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                }
                else ->{
                    false
                }
            }
            true
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called!")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called!")
    }



}