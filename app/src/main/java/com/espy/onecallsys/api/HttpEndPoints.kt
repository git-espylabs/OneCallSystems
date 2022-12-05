package com.espy.onecallsys.api

internal class HttpEndPoints {

    companion object{
        const val WS_URL_PRODUCTION = "https://onecallsystems.espylabs.com/public/"

        //Common Endpoints
        const val ONECALL_LOGIN = "api/login"
        const val ONECALL_PAY_TYPES = "api/paymemnt_type"
        const val IMAGE_ASSETS = WS_URL_PRODUCTION + "assets/images/"
        const val ONECALL_PROFILE = "api/peofileexe"
        const val ONECALL_APPLY_LEAVE = "api/leaveinsert"
        const val ONECALL_EXPENSE_TYPES = "api/expense_types"
        const val ONECALL_ADD_EXPENSE = "api/expenseinsert"

        //Product Endpoints
        const val ONECALL_PRODUCTS_LIST = "api/productall"
        const val ONECALL_CATEGORIES_LIST = "api/category"
        const val ONECALL_PRODUCTS_BY_CATEGORY = "api/productlist"
        const val ONECALL_PRODUCT_IMAGES = "api/product_images"
        const val ONECALL_PRODUCT_LIVE_STOCK = "api/livestock"
        const val ONECALL_PRODUCT_IMAGE_SYNC = "api/all_product_images"

        //Order Endpoints
        const val ONECALL_ORDER_POST = "api/placeorder"

        //Attendance Endpoints
        const val ONECALL_ATTENDANCE_IN = "api/punch_in"
        const val ONECALL_ATTENDANCE_OUT = "api/punchout"

        //Shop Endpoints
        const val ONECALL_SHOPS_LIST = "api/shoplist"
        const val ONECALL_SHOP_FEEDBACK = "api/shopfeedbk"
        const val ONECALL_SHOP_OUTSTANDING = "api/oustanding"
        const val ONECALL_SHOP_PAY_COLLECTION = "api/paymentcollection"
        const val ONECALL_SHOP_IN = "api/shopin"
        const val ONECALL_SHOP_OUT = "api/shopout"
        const val ONECALL_SHOP_ADD_SHOP = "api/add_shop"
        const val ONECALL_ROUTE_LIST = "api/route_list"
        const val ONECALL_DELIVERY_SHOPS_LIST = "api/deliveryshop_orders"
        const val ONECALL_PENDING_ORDERS = "api/salesexe_orders"
        const val ONECALL_PENDING_ORDER_ITEMS = "api/order_items"
        const val ONECALL_DELIVERY_SHOPS_LIST2 = "api/salesexes_shop_orders"
        const val ONECALL_TODAY_MY_ORDERS_LIST = "api/todaymyorders"
        const val ONECALL_SHOP_COLLECTION_HISTORY_LIST = "api/shopcollectionhistory"
    }

}