package salva.perez.kotlinmvproject.domain.model

    data class Vouchers(val products: List<Product>)
    data class Product(val code: String, val name: String, val price: Double)


