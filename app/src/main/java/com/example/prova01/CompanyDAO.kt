package com.example.prova01

import Company

import android.content.Intent

class CompanyDAO {
    companion object {
        var companies = mutableListOf<Company>()

        fun addCompany(company: Company) {
            companies.add(company)
        }

        fun getCompanies(): MutableList<Company> {
            return companies
        }

        fun getCompanyByCNPJ(cnpj: String): Company? {
            for (company in companies) {
                if (company.cnpj == cnpj) {
                    return company
                }
            }
            return null
        }

        fun getCompanyByName(name: String): Company? {
            for (company in companies) {
                if (company.name == name) {
                    return company
                }
            }
            return null
        }

        fun removeCompany(company: Company) {
            companies.remove(company)
        }

        fun removeCompanyByName(name: String) {
            for (company in companies) {
                if (company.name == name) {
                    companies.remove(company)
                }
            }
        }
    }
}