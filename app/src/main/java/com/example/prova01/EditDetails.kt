package com.example.prova01

import android.widget.EditText
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class EditDetails : AppCompatActivity() {
    lateinit var et_name : EditText
    lateinit var et_cnpj : EditText
    lateinit var et_cash : EditText
    lateinit var et_capacity : EditText
    lateinit var et_qty_sits : EditText
    lateinit var cb_airC : CheckBox

    lateinit var radioGroup : RadioGroup

    lateinit var bt_update : Button
    lateinit var bt_delete : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_details)

        //tela de update de empresa
        et_name = findViewById(R.id.name)
        et_cnpj = findViewById(R.id.cnpj)

    }

}