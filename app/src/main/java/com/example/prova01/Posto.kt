class Posto constructor(name: String, cnpj: String, cash: Float, capacity: Int) : Company(name, cnpj, cash) {
    var capacity: Int

    init {
        this.capacity = capacity
    }

    override fun toString(): String {
        return "Posto(name='$name', cnpj='$cnpj', cash=$cash, capacity=$capacity)"
    }
}