package com.don.backgroundforeground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.don.backgroundforeground.MySharedPreference
import com.don.backgroundforeground.R
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.btnSave
import kotlinx.android.synthetic.main.activity_second.etInput

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        etInput.setText(MySharedPreference.getEditTextValue(this))

        btnSave.setOnClickListener {
            if (!TextUtils.isEmpty(etInput.text.toString())) {
                MySharedPreference.setEditTextValue(this, etInput.text.toString())
                Toast.makeText(this, etInput.text.toString(), Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(
                    this,
                    getString(R.string.alert_empty_field),
                    Toast.LENGTH_SHORT
                ).show()

        }

        btnBack.setOnClickListener { finish() }

    }

    override fun onResume() {
        super.onResume()
        etInput.setText(MySharedPreference.getEditTextValue(this))
    }
}
