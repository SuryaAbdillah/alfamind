package com.example.alfamind.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.alfamind.R

data class Product(
    @DrawableRes val imageProdukId: Int,
    @StringRes val harga: Int
)

val products = listOf(
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price),
    Product(R.drawable.produk, R.string.produk_price)
)