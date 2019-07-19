package example.android.com.datasynchronization.roomdao

import androidx.room.Database
import androidx.room.RoomDatabase
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.roomdao.TeamDao

@Database(entities = arrayOf(Team::class),version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun getTeamDao():TeamDao

}