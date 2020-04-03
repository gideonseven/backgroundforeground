package com.don.backgroundforeground.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.don.backgroundforeground.MySharedPreference
import com.don.backgroundforeground.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        btnNext.setOnClickListener() {
            startActivity(Intent(this, SecondActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        etInput.setText(MySharedPreference.getEditTextValue(this))
    }


}
