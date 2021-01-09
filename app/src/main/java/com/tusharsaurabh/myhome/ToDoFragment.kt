package com.tusharsaurabh.myhome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 * Use the [ToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoFragment : Fragment() {

    lateinit var myCanvasView:MyCanvasView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myCanvasView = MyCanvasView(requireContext())
        val v: View = inflater.inflate(R.layout.fragment_to_do, container, false)
        val imageView: LinearLayout = v.findViewById(R.id.mylinearlayout)
        imageView.addView(myCanvasView)
        return v
    }

    fun clearDrawing(){
        myCanvasView.clearDrawing()
    }
}