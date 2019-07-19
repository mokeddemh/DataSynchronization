package example.android.com.datasynchronization.roomdao

import androidx.room.*
import example.android.com.datasynchronization.entity.Team

@Dao
interface TeamDao {

    @Query("select * from teams")
    fun getTeams():List<Team>

    @Query("select * from teams where isSynchronized=0")
    fun getTeamsToSynchronize():List<Team>


    @Insert
    fun addTeam(vararg team: Team)

    @Update
    fun updateTeam(teams: List<Team>)

    @Delete
    fun deleteTeams(teams: List<Team>)

}


