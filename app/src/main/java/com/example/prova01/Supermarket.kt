class Supermarket(name: String, cnpj: String, cash: Float, a_c: Boolean) : Company(name, cnpj, cash) {
    var a_c: Boolean

    init {
        this.a_c = a_c
    }

    override fun toString(): String {
        return "Supermarket(name='$name', cnpj='$cnpj', cash=$cash, Ar condicionado=$a_c)"
    }
}