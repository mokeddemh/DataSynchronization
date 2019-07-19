package example.android.com.datasynchronization

import android.app.Application
import example.android.com.datasynchronization.roomdao.RoomService

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}