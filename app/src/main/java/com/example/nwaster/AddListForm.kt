package com.example.nwaster

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity

import android.content.Intent
import android.widget.EditText
import android.widget.Toast


class AddListForm: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listform)

        val submit_button : Button = findViewById(R.id.btn_submit)
        val val_name_list:EditText = findViewById(R.id.val_name_list)
        val val_notes_list:EditText = findViewById(R.id.val_notes)
        submit_button.setOnClickListener{
            if (val_name_list.text.toString() == null || val_name_list.text.toString().length == 0)
            {
                Toast.makeText(this,"YOU HAVE TO PUT A NAME ON THE LIST", Toast.LENGTH_SHORT).show()
            }
            else {
                val returnIntent = Intent()
                returnIntent.putExtra("name_list", val_name_list.text.toString())
                returnIntent.putExtra("notes_list", val_notes_list.text.toString())
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}