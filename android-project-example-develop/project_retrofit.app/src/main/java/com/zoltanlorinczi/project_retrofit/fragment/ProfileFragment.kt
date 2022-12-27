package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.viewmodel.*

/**
 * Author:  Zoltan Lorinczi
 * Date:    12/2/2021
 */class ProfileFragment : Fragment() {
    companion object {
        private val TAG: String = javaClass.simpleName
    }
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())
        profileViewModel = ViewModelProvider(requireActivity(), factory)[ProfileViewModel::class.java]

        Log.d("ProfileFragment", profileViewModel.profile.value.toString())
        Log.d("ProfileFragment", profileViewModel.name.toString())

        val navbartitle: TextView = requireActivity().findViewById(R.id.toolbar_title)
        navbartitle.setText("My Profile");
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Log.d("ProfileFragment","OnCreateView Called!")
        val view = inflater.inflate(R.layout.profile_fragment, container, false)


        val nameText : TextView = view.findViewById(R.id.profileName)
        val emailText : TextView = view.findViewById(R.id.profileEmail)

        val phoneText : TextView = view.findViewById(R.id.profilePhone)
        val locationText : TextView = view.findViewById(R.id.profileLocation)

        profileViewModel.profile.observe(viewLifecycleOwner) {
            Log.d(ProfileFragment.TAG, "Profile = $it")
            nameText.text = profileViewModel.profile.value?.firstName + " " + profileViewModel.profile.value?.lastName
            emailText.text = profileViewModel.profile.value?.emailAddress
            phoneText.text = profileViewModel.profile.value?.phoneNumber
            locationText.text = profileViewModel.profile.value?.location
        }

        val navBar : BottomNavigationView = requireActivity().findViewById(R.id.bottomNavBar)
        navBar.visibility = View.VISIBLE

        return view
    }
}