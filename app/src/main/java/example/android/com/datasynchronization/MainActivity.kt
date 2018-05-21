package example.android.com.datasynchronization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import example.android.com.datasynchronization.entity.Team
import example.android.com.datasynchronization.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}
