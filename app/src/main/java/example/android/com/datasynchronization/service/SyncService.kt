package example.android.com.datasynchronization.service

import com.firebase.jobdispatcher.JobParameters
import com.firebase.jobdispatcher.JobService
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.retrofit.RetrofitService
import example.android.com.datasynchronization.roomdatabase.RoomService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SyncService:JobService() {

    var  jobParameters:JobParameters? = null

    override fun onStartJob(job: JobParameters?): Boolean {
        this.jobParameters = job
        val teams = RoomService.appDataBase.getTeamDao().getTeams()
        addTeams(teams)
        return  true
    }

    override fun onStopJob(job: JobParameters?)= true


    fun addTeams(teams:List<Team>) {

    val result = RetrofitService.endpoint.addTeams(teams)

    result.enqueue(object: Callback<String> {

        override fun onFailure(call: Call<String>?, t: Throwable?) {

            jobFinished(jobParameters!!,true)
        }

        override fun onResponse(call: Call<String>?, response: Response<String>?) {

            if(response?.isSuccessful!!) {
                RoomService.appDataBase.getTeamDao().deleteTeams(teams)
                jobFinished(jobParameters!!,false)


            }
            else
            {
                jobFinished(jobParameters!!,true)
            }
        }

    })
}


}
