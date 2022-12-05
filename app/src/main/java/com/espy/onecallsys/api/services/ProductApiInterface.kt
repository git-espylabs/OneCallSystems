package com.espy.onecallsys.api.services

import com.espy.onecallsys.api.HttpEndPoints
import com.espy.onecallsys.api.models.login.ProductAllStaffRequest
import com.espy.onecallsys.api.models.products.*
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApiInterface {

    @POST(HttpEndPoints.ONECALL_PRODUCTS_LIST)
    fun getProductsListAsync(@Body ProductAllStaffRequest: ProductAllStaffRequest): Deferred<Products>

    @GET(HttpEndPoints.ONECALL_CATEGORIES_LIST)
    fun getCategoriesListAsync(): Deferred<Categories>

    @POST(HttpEndPoints.ONECALL_PRODUCTS_BY_CATEGORY)
    fun getProductsByCategoryAsync(
        @Body productsByCategoryRequest: ProductsByCategoryRequest
    ): Deferred<Products>

    @POST(HttpEndPoints.ONECALL_PRODUCT_IMAGES)
    fun getProductImagesAsync(
        @Body productImageRequest: ProductImageRequest
    ): Deferred<ProductImageResponse>

    @POST(HttpEndPoints.ONECALL_PRODUCT_LIVE_STOCK)
    fun getProductLiveStockAsync(
        @Body productStockRequest: ProductStockRequest
    ): Deferred<ProductStockResponse>

    @GET(HttpEndPoints.ONECALL_PRODUCT_IMAGE_SYNC)
    fun getProductsImagesAsync(): Deferred<ProductsImagesData>
}