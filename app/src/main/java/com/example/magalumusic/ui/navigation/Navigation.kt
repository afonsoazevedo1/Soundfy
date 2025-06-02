
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.magalumusic.presentation.artists.ArtistScreen
import com.example.magalumusic.presentation.auth.LoginScreen
import com.example.magalumusic.presentation.home.HomeScreen
import com.example.magalumusic.presentation.playlist.PlaylistScreen
import com.example.magalumusic.presentation.profile.ProfileScreen
import com.example.magalumusic.ui.navigation.Routes

@Composable
fun MagaluMusicNavHost() {
    val navController = rememberNavController()

    //ROTA LOGIN
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.HOME,
        ) {
            HomeScreen(navController)
        }

        //ROTA ALBUNS
        composable(
            route = "${Routes.ARTIST}?artistName={artistName}&artistImage={artistImage}",
            arguments = listOf(
                navArgument("artistName") { type = NavType.StringType },
                navArgument("artistImage") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val artistName = backStackEntry.arguments?.getString("artistName") ?: return@composable
            val artistImage = backStackEntry.arguments?.getString("artistImage") ?: return@composable

            val avatarUri = if (artistImage.isNotBlank()) artistImage.toUri() else null

            ArtistScreen(
                artistName = Uri.decode(artistName),
                navController = navController,
                avatarUri = avatarUri
            )
        }
        //ROTA PLAYLIST
        composable(Routes.PLAYLISTS) {
            PlaylistScreen(navController)
        }

        //ROTA PROFILE
        composable(
            route = Routes.PROFILE,
        ) { backStackEntry ->

            ProfileScreen(
                navController = navController,
            )
        }

    }
}
