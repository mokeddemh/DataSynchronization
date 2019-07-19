package example.android.com.datasynchronization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.roomdao.RoomService
import example.android.com.datasynchronization.service.SyncService
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{

           val  team = Team(teamName = editTextName.text.toString(),continent = editTextContinent.text.toString())
            RoomService.appDataBase.getTeamDao().addTeam(team)
            editTextName.text.clear()
            editTextContinent.text.clear()
            scheduleSycn()






        }

    }






    private fun scheduleSycn() {
        val constraints = Constraints.Builder().
                setRequiredNetworkType(NetworkType.UNMETERED).
               // setRequiresBatteryNotLow(true).
                build()
        val req= OneTimeWorkRequest.Builder (SyncService::class.java).
                setConstraints(constraints).addTag("id1").
                build()
        val workManager = WorkManager.getInstance()
        workManager.enqueueUniqueWork("work",ExistingWorkPolicy.REPLACE,req)

    }

}
