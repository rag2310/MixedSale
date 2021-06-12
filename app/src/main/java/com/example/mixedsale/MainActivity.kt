package com.example.mixedsale

import AppDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mixedsale.model.User
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*GlobalScope.launch {

        }*/
    }


    private suspend fun createDatabase() {

        coroutineScope {
            launch {
                val appDatabase: AppDatabase = AppDatabase.getDatabase(applicationContext)
                appDatabase.let {
                    val user: User = User(
                        name = "Rodolfo"
                    )
                    it.userDao().insertAll(user)
                }
            }
        }
    }
}