package com.tusharsaurabh.myhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tusharsaurabh.myhome.databinding.FragmentLightsBinding

class LightsFragment : Fragment() {

    val bundle = Bundle()
    var sensorObj = SensorObject("desk","1", "led",arrayListOf<String>("On","Off"))

    val sensorList = SensorList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLightsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_lights,container,false)

        val fragmentManager = getFragmentManager()
        val fragmentTransaction = fragmentManager?.beginTransaction()

        //GetSensors

        sensorList.addSensor(SensorObject("desk","1","led", arrayListOf<String>("WHITE","HALLO","DISCO","SEXYY")))
        sensorList.addSensor(SensorObject("den","2", "bulb",arrayListOf<String>("ON","OFF")))
        sensorList.addSensor(SensorObject("livingroom","3", "bulb",arrayListOf<String>("ON","OFF")))
        sensorList.addSensor(SensorObject("workroom","3", "bulb",arrayListOf<String>("ON","OFF")))

        //Display Fragment
        if (fragmentTransaction != null) {
            //for ( i in 0..5){
            bundle.putSerializable("key",sensorList)
            val frag = LightFragment()
            frag.arguments = bundle
            fragmentTransaction.add(R.id.mycontainer, frag)
            //fragmentTransaction.add(R.id.mycontainer,LightFragment(), "2")
            //}
            fragmentTransaction.commit()
        }
        //if (fragmentTransaction != null) {
        //    fragmentTransaction.commit()
        //}

        return binding.root
    }




}

