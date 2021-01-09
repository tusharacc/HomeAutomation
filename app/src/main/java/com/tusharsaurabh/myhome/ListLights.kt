package com.tusharsaurabh.myhome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 * Use the [ListLights.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListLights : Fragment(), View.OnClickListener {

    val bundle = Bundle()
    lateinit  var sensorObject:SensorObject
    val sensorList: SensorList = SensorList()

    companion object{
        val TAG = "myHomeListLightFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e(TAG, sensorList.toString())

        if (sensorList.isEmpty()) {
        sensorList.addSensor(SensorObject("desk","1","led",arrayListOf<String>("blue","night","red","white","off","on")))
        sensorList.addSensor(SensorObject("window","2", "disco",arrayListOf<String>("on","off")))
        sensorList.addSensor(SensorObject("den","3", "bulb",arrayListOf<String>("on","off")))
        sensorList.addSensor(SensorObject("work","4", "bulb",arrayListOf<String>("on","off")))
        }


        val v = inflater.inflate(R.layout.fragment_list_lights, container, false)
        val parentLinearLayout: LinearLayout = v.findViewById(R.id.linearLayoutLights)
        parentLinearLayout.setBackgroundResource(R.drawable.bulb)
        //parentLinearLayout.alpha = 0.1F

        for (item:SensorObject in sensorList.getSensors()){
            val imageButton: Button = Button(requireContext())
            imageButton.text = item.getName()
            //val imagePath:String = "disco"
            //imageButton.setImageResource(resources.getIdentifier(imagePath,"drawable","com.tusharsaurabh.myhome"))
            //imageButton.layoutParams = ViewGroup.LayoutParams(200,200)

            imageButton.setTag(R.string.name,sensorList.getSensorObjectForName(item.getName()))
            parentLinearLayout.addView(imageButton)
            imageButton.setOnClickListener(this)
        }


        return v
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {

            bundle.putSerializable("key", p0.getTag(R.string.name) as Serializable?)
            val frag = LightMode()
            frag.arguments = bundle
            //val action = SpecifyAmountFragmentDirections.confirmationAction(amount)

            Navigation.findNavController(p0).navigate(R.id.action_listLights_to_lightMode, bundle)
        }
    }


}