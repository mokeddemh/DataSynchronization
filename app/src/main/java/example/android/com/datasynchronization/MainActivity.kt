package example.android.com.datasynchronization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.firebase.jobdispatcher.*
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.retrofit.RetrofitService
import example.android.com.datasynchronization.roomdatabase.RoomService
import example.android.com.datasynchronization.service.SyncService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        val  dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))
        val myJob =
                dispatcher.newJobBuilder().setService(SyncService::class.java)
                        // Service identifier
                        .setTag("sycnTeam")
                        // Don't repeat
                        .setRecurring(false)
                        .setLifetime(Lifetime.FOREVER)
                        // Start between 60s and 120s
                        .setTrigger(Trigger.executionWindow(60,120))
                        // Ignore if the a service with the same Tag is launched
                        .setReplaceCurrent(false)
                        // Linear retry
                        .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                        // On Wi-Fi mode
                        .setConstraints(Constraint.ON_UNMETERED_NETWORK)
                        .build()
        dispatcher.mustSchedule(myJob)
    }

}
