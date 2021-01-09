package com.tusharsaurabh.myhome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 * Use the [LightMode.newInstance] factory method to
 * create an instance of this fragment.
 */
class LightMode : Fragment(),View.OnClickListener {

    companion object{
        val TAG = "myHomeLightMode"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bundle = getArguments()
        var v:View = inflater.inflate(R.layout.fragment_light_mode, container, false)
        if ( bundle != null) {
            val sensorObj = bundle.getSerializable("key") as SensorObject
            //sensorList.sensors.forEach { item: SensorObject->
            Log.e(TAG, sensorObj.getName() as String)
            Log.e(TAG, sensorObj.getMode()[0] as String)
            //}



            val parentLinearLayout: LinearLayout = v.findViewById(R.id.linearLayoutListMode)
            //parentLinearLayout.setBackgroundResource(R.drawable.bulb)



            for (item: String in sensorObj.getMode()) {
                val imageButton: Button = Button(requireContext())
                //imageButton.marginTop(200)
                imageButton.tag = item
                //imageButton.setImageResource(R.drawable.light_blue)
                val params = ViewGroup.LayoutParams(400, 400) as ViewGroup.LayoutParams
                //val margin = ViewGroup.MarginLayoutParams(20,30)
                //val margin = ViewGroup.MarginLayoutParams(10,10)
                imageButton.layoutParams = params
                when (item){
                    "blue" -> imageButton.setBackgroundResource(R.drawable.ic_blue)
                    "night" -> imageButton.setBackgroundResource(R.drawable.ic_disco)
                    "red" -> imageButton.setBackgroundResource(R.drawable.ic_red)
                    "off" -> imageButton.setBackgroundResource(R.drawable.ic_off)
                    "on" -> imageButton.setBackgroundResource(R.drawable.ic_on)
                    "white" -> imageButton.setBackgroundResource(R.drawable.ic_white)
                }

                //imageButton.setPadding(50,50,50,50)


                parentLinearLayout.addView(imageButton)
                imageButton.setOnClickListener(this)
            }
        }

        return v
    }

    override fun onClick(p0: View?) {
        Log.e(TAG, "I am clicked")
    }


}