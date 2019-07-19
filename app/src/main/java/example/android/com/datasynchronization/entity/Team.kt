package example.android.com.datasynchronization.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
        var teamName:String,
        var continent:String,
       var isSynchronized:Int =0
) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "team_id")
        var teamId:Int?=null
}