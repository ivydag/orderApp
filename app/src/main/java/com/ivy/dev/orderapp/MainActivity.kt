package com.ivy.dev.orderapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.ivy.dev.orderapp.data.AppDatabase
import com.ivy.dev.orderapp.network.MenuItemNetwork
import com.ivy.dev.orderapp.network.MenuNetworkData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {

                val context = LocalContext.current
                val sharedPreferences =
                    context.getSharedPreferences("order_preferences", Context.MODE_PRIVATE)
                val isUserRegister = sharedPreferences.getBoolean(REGISTER_USER, false)



                val navController = rememberNavController()
                MyNavigationComposable(navController, database.menuDao())


                if (isUserRegister) {
                    navController.navigate(Home.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                } else {
                    MyNavigationComposable(navController, menuDao = database.menuDao() )
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                val listMenu = fetchMenu()
                saveData(listMenu)
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val response =
            httpClient.get(urlString = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetworkData>()

        return response.menu
    }

    private fun saveData(listMenu: List<MenuItemNetwork>) {

        val menuItemsRoom = listMenu.map {
            it.toMenuItemRoom()
        }
        database.menuDao().insertAllMenu(*menuItemsRoom.toTypedArray())

    }


    companion object {
        const val REGISTER_USER = "REGISTER_USER"
        const val DATABASE_NAME = "menu_database.db"
    }
}