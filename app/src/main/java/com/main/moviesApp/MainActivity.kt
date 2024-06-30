package com.main.moviesApp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.TextView
import com.main.moviesApp.R
import com.main.moviesApp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }






    fun showDialog(massage :String?){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_linternet_not_found)
        var btn = dialog.findViewById<TextView>(R.id.okbtn)
        btn.setOnClickListener {
            dialog.dismiss()
            finishAffinity()
        }
        var text = dialog.findViewById<TextView>(R.id.ip_edit_text)
        text.text = massage
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthLcl = (displayMetrics.widthPixels * 0.9f).toInt()
        val paramsLcl = dialog.window?.attributes
        paramsLcl?.width = widthLcl
        paramsLcl?.gravity = Gravity.CENTER
        dialog.window?.attributes = paramsLcl
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

    }


}