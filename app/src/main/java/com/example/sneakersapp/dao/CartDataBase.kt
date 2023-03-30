package com.example.sneakersapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SneakerTable::class],
    version = 1
)
abstract class CartDataBase : RoomDatabase() {
    abstract fun getCartDao(): CartDataAccessObject
//    companion object {
//
//        @Volatile
//        private var instant: CartDataBase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instant?: synchronized(LOCK) {
//            instant ?: createDatabase(context).also { instant = it }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                CartDataBase::class.java,
//                "article_db_db"
//            ).build()
//    }
}
