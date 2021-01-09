package com.tusharsaurabh.myhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tusharsaurabh.myhome.databinding.FragmentHomeScreenBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen,container,false)
        binding.lightBtn.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeScreenFragment_to_listLights)
        }

        binding.toDoBtn.setOnClickListener{view: View ->
            Navigation.findNavController(view).navigate(R.id.action_homeScreenFragment_to_toDoFragment)
        }
        return binding.root
    }


}