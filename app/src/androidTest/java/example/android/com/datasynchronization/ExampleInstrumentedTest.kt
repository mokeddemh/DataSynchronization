package example.android.com.datasynchronization

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.roomdao.AppDataBase
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    var appDataBase: AppDataBase? = null

    @Before
    fun initDB() {
        appDataBase =
                Room.inMemoryDatabaseBuilder(InstrumentationRegistry
                        .getContext(), AppDataBase::class.java).build()
    }

    @After
    fun closeDb() {
        appDataBase?.close()
    }

    @Test
    fun insertAndGetTeams() {
        val team1 = Team(teamName = "Liverpool", continent = "Europe")
        val team2 = Team(teamName = "Manchester", continent = "Europe")
        val team3 = Team(teamName = "Real Madrid", continent = "Europe")
        appDataBase?.getTeamDao()?.addTeam(team1, team2, team3)
        val teams = appDataBase?.getTeamDao()?.getTeams()
        // toTypedArray() converts list to Array
        Assert.assertArrayEquals(teams?.toTypedArray(), mutableListOf(team1, team2, team3).toTypedArray())
    }


    }


