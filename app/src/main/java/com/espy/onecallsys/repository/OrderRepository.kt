package com.espy.onecallsys.repository

import com.espy.onecallsys.api.RestServiceProvider
import com.espy.onecallsys.api.Result
import com.espy.onecallsys.api.models.order.OrderRequestMaster
import com.espy.onecallsys.api.models.order.OrderResponse
import com.espy.onecallsys.db.DatabaseProvider
import com.espy.onecallsys.db.entities.OrderEntity
import com.espy.onecallsys.domain.toOrderProduct
import com.espy.onecallsys.ui.order.models.OrderProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OrderRepository: BaseRepository()  {

    override fun onCleared() {
    }

    val cartDao = DatabaseProvider.getOrder()

    suspend fun uploadOrder(orderRequestMaster: OrderRequestMaster): Result<OrderResponse> {
        return try {
            val response = RestServiceProvider
                .getOrderService()
                .uploadOrderAsync(orderRequestMaster)
                .await()
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun addProductToCart(product: OrderEntity): Flow<Int>{
        return flow{
            cartDao.insertProduct(product)?.let {
                cartDao.getCartProductsCount()?.let { numOfProducts ->
                    emit(numOfProducts)
                }
            }?: kotlin.run {
                emit(0)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCartProductsCount(): Flow<Int>{
        return flow{
            cartDao.getCartProductsCount()?.let { numOfProducts ->
                emit(numOfProducts)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun emptyCart() = cartDao.emptyCart()

    fun getAllCartItems(): List<OrderProduct>{
        return cartDao.getCartProducts().map {
            it.toOrderProduct()
        }
    }

    fun updateQuantity(id: String, qty: String) = cartDao.updateQuantity(id, qty)

    fun removeProduct(id: String) = cartDao.removeProduct(id)


}