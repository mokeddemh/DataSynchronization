package example.android.com.datasynchronization.service


import android.annotation.SuppressLint
import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.google.common.util.concurrent.ListenableFuture
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.retrofit.RetrofitService
import example.android.com.datasynchronization.roomdao.RoomService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SyncService(val ctx: Context, val workParamters: WorkerParameters):ListenableWorker(ctx, workParamters){

    lateinit var  future:SettableFuture<Result>

    @SuppressLint("RestrictedApi")
    override fun startWork(): ListenableFuture<Result> {

        future = SettableFuture.create<Result>()
        val teams = RoomService.appDataBase.getTeamDao().getTeamsToSynchronize()
        addTeams(teams)

        return future
    }



    @SuppressLint("RestrictedApi")

    fun addTeams(teams:List<Team>) {
    val result = RetrofitService.endpoint.addTeams(teams)
    result.enqueue(object: Callback<String> {

        override fun onFailure(call: Call<String>?, t: Throwable?) {

          future.set(Result.retry())


        }

        override fun onResponse(call: Call<String>?, response: Response<String>?) {

            if(response?.isSuccessful!!) {
                for (item in teams) {
                    item.isSynchronized = 1
                }
               RoomService.appDataBase.getTeamDao().updateTeam(teams)
               future.set(Result.success())

            }
            else
            {
               future.set(Result.retry())
            }
        }

    })
}


}
