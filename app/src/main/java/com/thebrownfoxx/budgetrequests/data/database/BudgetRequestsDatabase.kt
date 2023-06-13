package com.thebrownfoxx.budgetrequests.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thebrownfoxx.budgetrequests.data.models.budgetrequest.BudgetRequestEntity
import com.thebrownfoxx.budgetrequests.data.models.organization.OrganizationEntity
import com.thebrownfoxx.budgetrequests.data.models.user.CollegeAdminsEntity
import com.thebrownfoxx.budgetrequests.data.models.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
        CollegeAdminsEntity::class,
        OrganizationEntity::class,
        BudgetRequestEntity::class,
    ],
    version = 1,
)
abstract class BudgetRequestsDatabase : RoomDatabase() {
    abstract fun dao(): BudgetRequestsDao

    companion object {
        @Volatile
        private var instance: BudgetRequestsDatabase? = null

        fun getDatabase(context: Context): BudgetRequestsDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, BudgetRequestsDatabase::class.java, "item_database")
                    .build()
                    .also { instance = it }
            }
        }
    }

}
