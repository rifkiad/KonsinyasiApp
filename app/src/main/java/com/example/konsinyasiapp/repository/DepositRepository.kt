package com.example.konsinyasiapp.repository

import androidx.lifecycle.LiveData
import com.example.konsinyasiapp.dao.DepositDao
import com.example.konsinyasiapp.entities.DepositData
import com.example.konsinyasiapp.entities.DepositWithProduct
import com.example.konsinyasiapp.entities.DepositWithShop
import com.example.konsinyasiapp.entities.ProductInDeposit
import com.example.konsinyasiapp.entities.ProductInDepositWithProduct
import com.example.konsinyasiapp.entities.StatusDeposit
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DepositRepository(private val depositDao: DepositDao) {

    val getAllDeposit: LiveData<List<DepositData>> = depositDao.getAllDeposit()

    fun getAllDepositHome(): LiveData<List<DepositData>> = depositDao.getAllDepositHome()

    fun getAllShopWithDeposit(): LiveData<List<DepositWithShop>> {
        return depositDao.getDepositWithShop()
    }

    fun getAllDepositProduct(): LiveData<List<ProductInDepositWithProduct>> =
        depositDao.getAllDepositProduct()

    suspend fun updateDepositFinishDate(depositId: Long, date: String) {
        depositDao.updateDepositFinishDate(depositId, date)
    }

    suspend fun insertData(depositData: DepositData): Long {
        return depositDao.insertData(depositData)
    }

    suspend fun updateData(productInDepositData: ProductInDeposit) {
        depositDao.updateData(productInDepositData)
    }

    suspend fun deleteItem(depositData: DepositData) {
        depositDao.deleteItem(depositData)
    }

    suspend fun deleteAll() {
        depositDao.deleteAll()
    }

    fun getAllUnfinishedDeposit(): LiveData<List<DepositWithShop>> =
        depositDao.getAllUnfinishedDeposit()

    fun getProductDepositsForCurrentMonth(
        listDeposit: List<DepositData>,
        listProductInDeposit: List<ProductInDepositWithProduct>): List<ProductInDepositWithProduct> {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val currentListDeposit = listDeposit.filter { deposit ->
            val depositStartDate =
                SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID")).parse(deposit.depositDate)
            val calendar = Calendar.getInstance().apply {
                if (depositStartDate != null) {
                    time = depositStartDate
                }
            }
            calendar.get(Calendar.MONTH) == currentMonth && calendar.get(Calendar.YEAR) == currentYear
        }

        return listProductInDeposit.filter { productDeposit ->
            currentListDeposit.any { it.id == productDeposit.productInDeposit.idDeposit }
        }
    }

    fun calculateTotalProductSoldWithPrice(listProductDeposit: List<ProductInDepositWithProduct>): Int {
        var totalAmount = 0
        listProductDeposit.forEach { productDeposit ->
            val productPrice = productDeposit.product?.hargaProduct?.replace("[Rp,.\\s]".toRegex(), "")?.toIntOrNull()
            val totalProductSold = try {
                productDeposit.productInDeposit.soldProduct.toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (productPrice != null) {
                totalAmount += totalProductSold * productPrice
            }
        }
        return totalAmount
    }

    suspend fun updateDepositStatus(id: Long, newStatus: StatusDeposit) {
        depositDao.updateDepositStatus(id, newStatus)
    }
}