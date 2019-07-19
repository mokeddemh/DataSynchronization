package example.android.com.datasynchronization.retrofit

import example.android.com.datasynchronization.entity.Team
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Endpoint {

    @POST("addteams")
    fun addTeams(@Body teams: List<Team>):Call<String>

}