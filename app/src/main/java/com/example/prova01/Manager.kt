package com.example.prova01

import Cinema
import Company
import Posto
import Supermarket
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prova01.CompanyDAO.Companion.companies
import com.example.prova01.CompanyDAO.Companion.getCompanies
import java.io.Serializable
import java.util.Collections.addAll

class Manager : AppCompatActivity() {
    lateinit var lv_companies : ListView
    lateinit var bt_return : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)

        val list = intent.getSerializableExtra("lista") as? List<Company>

        if (list != null) {
            val adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            val listView = lv_companies
            listView.adapter = adapt
        }

        lv_companies.setOnItemClickListener { parent, view, position, id ->
            val clickedCompany = companies[position]
            val nomeEmpresa = clickedCompany.name
            val intent = Intent(this, EditDetails::class.java)
            intent.putExtra("name", nomeEmpresa as Serializable)
            startActivity(intent)
//            CompanyDAO.removeCompanyByName(nomeEmpresa)
        }

        bt_return = findViewById(R.id.bt_return)

        bt_return.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clear() {
        val companies = getCompanies()
        val adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, companies)
        val listView = lv_companies
        listView.adapter = adapt
    }
}