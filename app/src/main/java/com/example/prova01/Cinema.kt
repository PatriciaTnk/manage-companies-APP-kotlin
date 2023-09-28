class Cinema constructor(name: String, cnpj: String, cash: Float, qty_sits: Int) : Company(name, cnpj, cash) {
    var qty_sits: Int

    init {
        this.qty_sits = qty_sits
    }

    override fun toString(): String {
        return "Cinema(name='$name', cnpj='$cnpj', cash=$cash, capacity=$qty_sits)"
    }
}