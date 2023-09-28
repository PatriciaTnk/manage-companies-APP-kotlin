package com.example.prova01

import Cinema
import Company
import Posto
import Supermarket
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    lateinit var et_name : EditText
    lateinit var et_cnpj : EditText
    lateinit var et_cash : EditText
    lateinit var et_capacity : EditText
    lateinit var et_qty_sits : EditText

    lateinit var cb_airC : CheckBox

    lateinit var radioGroup : RadioGroup


    lateinit var bt_store : Button
    lateinit var bt_manage : Button

    lateinit var tv_resultado : TextView

    var lv_empresas : ListView? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                result.data?.let {
                    if (it.hasExtra("lista")) {
                        val lista: Company? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            it.getParcelableExtra("lista", Company::class.java)
                        } else {
                            it.getParcelableExtra("lista")
                        }
                        Log.i("TESTE","Veio de T2: "+lista)
                    }
                }
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_name = findViewById(R.id.name)
        et_cnpj = findViewById(R.id.cnpj)
        et_cash = findViewById(R.id.cash)
        et_capacity = findViewById(R.id.capacity)
        et_qty_sits = findViewById(R.id.qty_sits)

        cb_airC = findViewById(R.id.air_conditioning)

        radioGroup = findViewById(R.id.radioGroup)

        bt_store = findViewById(R.id.store)
        bt_manage = findViewById(R.id.manageCompanies)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.supermarket -> {
                    et_capacity.isEnabled = true
                    et_qty_sits.isEnabled = false
                    cb_airC.isEnabled = true
                }
                R.id.Posto -> {
                    et_capacity.isEnabled = true
                    et_qty_sits.isEnabled = false
                    cb_airC.isEnabled = false
                }
                R.id.cinema -> {
                    et_capacity.isEnabled = false
                    et_qty_sits.isEnabled = true
                    cb_airC.isEnabled = false
                }
            }
        }

        bt_store.setOnClickListener {
            var name = et_name.text.toString()
            var cnpj = et_cnpj.text.toString()

            // tratar excessão caso seja null

            var cash = et_cash.text.toString().toFloat()

            var company : Company? = null

            when (radioGroup.checkedRadioButtonId) {
                R.id.supermarket -> {
                    var air_conditionig = cb_airC.isChecked

                    company = Supermarket(name, cnpj, cash, air_conditionig)
                }
                R.id.Posto -> {
                    var capacity = et_capacity.text.toString().toInt()

                    company = Posto(name, cnpj, cash, capacity)
                }
                R.id.cinema -> {
                    var qty_sits = et_qty_sits.text.toString().toInt()

                    company = Cinema(name, cnpj, cash, qty_sits)
                }
            }

            if (company != null) {
                CompanyDAO.addCompany(company)
                Toast.makeText(this, "Empresa cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            }

            val adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, CompanyDAO.getCompanies())
            lv_empresas?.adapter = adapt

            bt_manage.setOnClickListener {

                Intent(this, Manager::class.java).let {
                    it.putExtra("lista", CompanyDAO.getCompanies() as Serializable)
                    register.launch(it)
                }
            }

            fun toaster(msg: String){
                Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            }

            fun clean() {
                et_name.setText("")
                et_cnpj.setText("")
                et_cash.setText("")
                et_capacity.setText("")
                et_qty_sits.setText("")
                cb_airC.isChecked = false
            }

        }
    }

}

