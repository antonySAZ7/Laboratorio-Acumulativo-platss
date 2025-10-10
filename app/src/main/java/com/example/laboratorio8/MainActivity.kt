// Antony Saz - 24710
// Laboratorio 11 - Room Database
package com.example.laboratorio8

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio8.data.local.UserPreferences
import com.example.laboratorio8.ui.navigation.CharactersRoot
import com.example.laboratorio8.ui.navigation.LocationsRoot
import com.example.laboratorio8.ui.navigation.Login
import com.example.laboratorio8.ui.navigation.ProfileRoot
import com.example.laboratorio8.ui.navigation.charactersNav
import com.example.laboratorio8.ui.navigation.locationsNav
import com.example.laboratorio8.ui.screens.LoginScreen
import com.example.laboratorio8.ui.screens.profile.ProfileScreen
import com.example.laboratorio8.ui.theme.Laboratorio8Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("ContextCastToActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio8Theme {
                val context = LocalContext.current
                val userPreferences = remember { UserPreferences(context) }
                val userName by userPreferences.userName.collectAsState(initial = null)
                val nav = rememberNavController()
                var selected by remember { mutableStateOf<Any>(CharactersRoot) }


                LaunchedEffect(userName) {
                    if (userName == null) {
                        nav.navigate(Login) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                }

                Scaffold(
                    bottomBar = {

                        if (userName != null) {
                            NavigationBar {
                                listOf(
                                    Triple(CharactersRoot as Any, "Characters", Icons.Filled.Group),
                                    Triple(LocationsRoot as Any, "Locations", Icons.Filled.Place),
                                    Triple(ProfileRoot as Any, "Profile", Icons.Filled.AccountCircle)
                                ).forEach { (dest, label, icon) ->
                                    val isSelected = selected == dest
                                    NavigationBarItem(
                                        selected = isSelected,
                                        onClick = {
                                            selected = dest
                                            nav.navigate(dest) {
                                                popUpTo(nav.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = { Icon(icon, contentDescription = label) },
                                        label = { Text(label) }
                                    )
                                }
                            }
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = nav,
                        startDestination = if (userName != null) CharactersRoot else Login,
                        modifier = Modifier.padding(padding)
                    ) {

                        charactersNav(nav)


                        locationsNav(nav)

                        composable<ProfileRoot> {
                            ProfileScreen(
                                fullName = "Antony Saz",
                                carnet = "24710",
                                onLogout = {
                                    nav.navigate(Login) {
                                        popUpTo(0) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }


                        composable<Login> {
                            val activity = LocalContext.current as Activity
                            BackHandler(enabled = true) { activity.finish() }

                            LoginScreen(
                                onLoginClick = {
                                    selected = CharactersRoot
                                    nav.navigate(CharactersRoot) {
                                        popUpTo<Login> { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}