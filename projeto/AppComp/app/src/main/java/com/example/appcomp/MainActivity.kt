package com.example.appcomp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener,
    CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_toast.setOnClickListener(this)
        button_snack.setOnClickListener(this)
        button_get_spinner.setOnClickListener(this)
        button_set_spinner.setOnClickListener(this)
        button_set_seekbar.setOnClickListener(this)
        button_get_seekbar.setOnClickListener(this)

        spinner_static.onItemSelectedListener = this
        seekbar.setOnSeekBarChangeListener(this)

        switch_on_off.setOnCheckedChangeListener(this)
        check_on_of.setOnCheckedChangeListener(this)

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
            R.id.button_get_spinner -> {
                val selectedItem = spinner_static.selectedItem
                val selectedItemId = spinner_static.selectedItemId
                val selectedItemPosition = spinner_static.selectedItemPosition

                toast("Position: $selectedItemId: $selectedItem")
            }
            R.id.button_set_spinner -> {
                spinner_static.setSelection(2)
            }
            R.id.button_get_seekbar -> {
                toast("Seekbar: ${seekbar.progress}")
            }
            R.id.button_set_seekbar -> {
                seekbar.progress = 15 
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        when (buttonView.id) {
            R.id.switch_on_off -> {
                toast("Switch: ${if (isChecked) "true" else "false"}")
//                switch_on_off.isChecked = true
            }
            R.id.check_on_of -> {
                toast("Checkbox: ${if (isChecked) "true" else "false"}")
//                check_on_of.isChecked = true
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        toast("nothing")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spinner_static -> {
                toast(parent?.getItemAtPosition(position).toString())
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        text_seekbar_value.text = "Valor seekbar: $progress"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        toast("Track started")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        toast("Track stoped")
    }

    private fun loadSpinner() {
        val mList = listOf("Gramas", "Kg", "Arroba", "Tonelada")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic.adapter = adapter
    }

    private fun toast(str: String) {
        val toast = Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

    }
}