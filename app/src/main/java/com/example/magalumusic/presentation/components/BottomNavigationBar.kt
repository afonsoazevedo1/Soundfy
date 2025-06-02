import android.net.Uri
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.magalumusic.ui.navigation.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import com.example.magalumusic.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String,
    avatarUri: Uri?
) {
    val encodedAvatar = URLEncoder.encode(avatarUri?.toString() ?: "", StandardCharsets.UTF_8.toString())

    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_disc),
                    contentDescription = "Artistas"
                )
            },
            label = { Text("Artistas") },
            selected = currentRoute == Routes.HOME || currentRoute == Routes.ARTIST,
            onClick = {
                navController.navigate("${Routes.HOME}?avatarUri=$encodedAvatar") {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = "Playlists"
                )
            },
            label = { Text("Playlists") },
            selected = currentRoute == Routes.PLAYLISTS,
            onClick = {
                navController.navigate(Routes.PLAYLISTS) {
                    popUpTo(Routes.HOME) { inclusive = false }
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_disc),
                    contentDescription = "Perfil"
                )
            },
            label = { Text("Perfil") },
            selected = currentRoute == Routes.PROFILE,
            onClick = {
                navController.navigate(Routes.PROFILE) {
                    popUpTo(Routes.HOME) { inclusive = false }
                }
            }
        )
    }
}
