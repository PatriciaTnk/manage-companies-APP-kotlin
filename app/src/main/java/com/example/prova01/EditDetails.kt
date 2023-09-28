package com.example.prova01

import Cinema
import Posto
import Supermarket
import android.content.Intent
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
    lateinit var bt_return : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_details)

        //tela de update de empresa
        et_name = findViewById(R.id.name)
        et_cnpj = findViewById(R.id.cnpj)
        et_cash = findViewById(R.id.cash)
        et_capacity = findViewById(R.id.capacity)
        et_qty_sits = findViewById(R.id.qty_sits)

        cb_airC = findViewById(R.id.air_conditioning)

        radioGroup = findViewById(R.id.radioGroup)

        bt_update = findViewById(R.id.bt_update)
        bt_delete = findViewById(R.id.bt_delete)
        bt_return = findViewById(R.id.bt_back)

        //função para preencher os campos com os dados da empresa ao abrir a tela de update
        val name = this.intent.getStringExtra("name")
        val company = name?.let { CompanyDAO.getCompanyByName(it) }

        //função para aparecer e desaparecer os campos de acordo com o tipo de empresa

        if (company != null) {
            et_name.setText(company.name)
            et_cnpj.setText(company.cnpj)
            et_cash.setText(company.cash.toString())
            when (company) {
                is Supermarket -> {
                    cb_airC.isChecked = company.a_c
                    et_capacity.isEnabled = true
                    et_qty_sits.isEnabled = false
                    cb_airC.isEnabled = true
                }
                is Cinema -> {
                    et_qty_sits.setText(company.qty_sits.toString())
                    et_capacity.isEnabled = false
                    et_qty_sits.isEnabled = true
                    cb_airC.isEnabled = false
                }
                is Posto -> {
                    et_capacity.setText(company.capacity.toString())
                    et_capacity.isEnabled = true
                    et_qty_sits.isEnabled = false
                    cb_airC.isEnabled = false
                }
            }
        }

        bt_update.setOnClickListener {
            var name = et_name.text.toString()
            var cnpj = et_cnpj.text.toString()
            var cash = et_cash.text.toString().toFloat()
            var capacity = et_capacity.text.toString().toInt()
            var qty_sits = et_qty_sits.text.toString().toInt()
            var airC = cb_airC.isChecked

            var company = CompanyDAO.getCompanyByName(name)

            if (company != null) {
                CompanyDAO.removeCompanyByName(name)
                when (company) {
                    is Supermarket -> {
                        var newCompany = Supermarket(name, cnpj, cash, airC)
                        CompanyDAO.addCompany(newCompany)
                    }
                    is Cinema -> {
                        var newCompany = Cinema(name, cnpj, cash, qty_sits)
                        CompanyDAO.addCompany(newCompany)
                    }
                    is Posto -> {
                        var newCompany = Posto(name, cnpj, cash, capacity)
                        CompanyDAO.addCompany(newCompany)
                    }
                }
            }
        }

        bt_delete.setOnClickListener {
            var name = et_name.text.toString()
            var company = CompanyDAO.getCompanyByName(name)
            if (company != null) {
                CompanyDAO.removeCompanyByName(name)
            }
        }

        bt_return.setOnClickListener {
            val intent = Intent(this, Manager::class.java)
            startActivity(intent)
        }

    }

}