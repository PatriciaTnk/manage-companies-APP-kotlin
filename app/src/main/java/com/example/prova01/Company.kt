open class Company(name: String, cnpj: String, cash: Float) {
    var name: String
    var cnpj: String
    var cash: Float

    init {
        this.name = name
        this.cnpj = cnpj
        this.cash = cash
    }

    override fun toString(): String {
        return "Company(name='$name', cnpj='$cnpj', cash=$cash)"
    }
}

