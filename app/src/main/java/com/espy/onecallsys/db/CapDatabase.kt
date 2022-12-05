package com.espy.onecallsys.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.espy.onecallsys.db.dao.OrderDao
import com.espy.onecallsys.db.dao.ProductsDao
import com.espy.onecallsys.db.dao.ShopsDao
import com.espy.onecallsys.db.entities.OrderEntity
import com.espy.onecallsys.db.entities.ProductsEntity
import com.espy.onecallsys.db.entities.ShopsEntity

@Database(
    version = 2,
    exportSchema = false,
    entities = [
        OrderEntity::class,
        ProductsEntity::class,
        ShopsEntity::class,
    ]
)

abstract class CapDatabase : RoomDatabase()  {
    abstract val orderDao: OrderDao
    abstract val productsDao: ProductsDao
    abstract val shopsDao: ShopsDao
}