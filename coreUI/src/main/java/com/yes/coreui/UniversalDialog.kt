package com.yes.coreui

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class UniversalDialog : DialogFragment() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var cancel_btn:ImageButton
    private lateinit var ok_btn:ImageButton
    //private lateinit var dialog_layout: FrameLayout
    private var myDialog: Dialog?=dialog
    public lateinit var binding:ViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
    }

    abstract var layout:Int
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //dialog_layout = inflater.inflate(layout, null, false) as FrameLayout //files_list
        val lp = dialog!!.window!!.attributes
        lp.dimAmount = 0.6f // уровень затемнения от 1.0 до 0.0
        dialog!!.window!!.attributes = lp
        dialog!!.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        /////////////////////
        /////////////////////
        myDialog=dialog
        //myDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        ///////////////////

        ///////////////////
        myDialog!!.setContentView(layout)

        val disp: Display = (requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val outSmallestSize = Point()
        val outLargestSize = Point()
        disp.getCurrentSizeRange(outSmallestSize, outLargestSize)

        val screeSize1 = Point()
        //todo
/*
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
              val rect: Rect =(requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).currentWindowMetrics.bounds
         } else {
              disp.getSize(screeSize1)
          }*/

        disp.getSize(screeSize1)
        binding.root.minimumWidth = (screeSize1.x * 0.9f).toInt()
        binding.root.minimumHeight = (screeSize1.y * 0.9f).toInt()
        dialog!!.window!!.setLayout(
            (screeSize1.x * 0.92f).toInt(),
            (screeSize1.y * 0.92f).toInt(),
        )
        dialog!!.window!!.setLayout(
            (screeSize1.x * 0.9f).toInt(),
            (screeSize1.y * 0.9f).toInt()
        )

        isCancelable = false
        /////////////////////////////
        /////////////////////////

        dialog!!.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        /////////////////////////////
        return binding.root
    }

}