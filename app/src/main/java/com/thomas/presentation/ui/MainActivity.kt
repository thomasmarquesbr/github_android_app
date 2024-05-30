package com.thomas.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thomas.presentation.ui.screens.userdetails.UserDetailsScreen
import com.thomas.presentation.ui.screens.userdetails.UserDetailsViewModel
import com.thomas.presentation.ui.screens.users.UsersScreen
import com.thomas.presentation.ui.screens.users.UsersViewModel
import com.thomas.presentation.ui.theme.GitHubAppTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val USERS_SCREEN = "/users"
private const val USER_DETAILS_SCREEN = "/users/{username}"

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = USERS_SCREEN
                    ) {
                        composable(USERS_SCREEN) {
                            UsersScreen(
                                viewModel = koinViewModel<UsersViewModel> {
                                    parametersOf(::goToUserDetails)
                                }
                            )
                        }

                        composable(USER_DETAILS_SCREEN) { navBackStackEntry ->
                            val param = navBackStackEntry.arguments?.getString("username")
                            param?.let { username ->
                                UserDetailsScreen(
                                    viewModel = koinViewModel<UserDetailsViewModel> {
                                        parametersOf(username)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun goToUserDetails(username: String) {
        if (::navController.isInitialized) {
            navController.navigate(USER_DETAILS_SCREEN.replace("{username}", username))
        }
    }
}