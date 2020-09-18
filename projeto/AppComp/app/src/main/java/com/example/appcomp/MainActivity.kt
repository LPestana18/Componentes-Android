package com.example.appcomp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_toast.setOnClickListener(this)
        button_snack.setOnClickListener(this)

        loadSpinner()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_toast -> {
                val toast = Toast.makeText(this, "TOAST", Toast.LENGTH_LONG)

//                val textView = toast.view?.findViewById<TextView>(android.R.id.message)
//                textView?.setTextColor(Color.RED)

                toast.setGravity(Gravity.TOP, 0, 250)

                val layout = layoutInflater.inflate(R.layout.toast_layout, null)
                toast.view = layout

                toast.show()
            }
            R.id.button_snack -> {
                val snack = Snackbar.make(linear_root, "Snack", Snackbar.LENGTH_LONG)

                snack.setAction("Desafazer", View.OnClickListener {
                    toast("Desfeito!")
                })

                snack.setActionTextColor(Color.BLUE)
                snack.setBackgroundTint(Color.GRAY)

                snack.show()
            }
        }
    }

    private fun loadSpinner() {
        val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic.adapter = adapter
    }

    private fun toast(str: String) {
        val toast = Toast.makeText(this, str, Toast.LENGTH_LONG).show()

    }

}