package com.example.prova01

import Company
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class Manager : AppCompatActivity() {
    lateinit var lv_companies: ListView
    lateinit var bt_return: Button

    @SuppressLint("MissingInflatedId")
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
                    }
                }
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage)

        // Obtenha a lista de empresas do CompanyDAO
        val list = CompanyDAO.companies

        if (list.isNotEmpty()) {
            val adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            lv_companies = findViewById(R.id.lv_companies) // Corrigi a inicialização
            lv_companies.adapter = adapt
        }

        lv_companies.setOnItemClickListener { parent, view, position, id ->
            val clickedCompany = CompanyDAO.companies[position]
            val nomeEmpresa = clickedCompany.name

            Intent(this, Manager::class.java).let {
                it.putExtra("name", nomeEmpresa as Serializable)
                register.launch(it)
            }
        }

        bt_return = findViewById(R.id.bt_return)

        bt_return.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
