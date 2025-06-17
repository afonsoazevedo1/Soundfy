import android.net.Uri
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.soundfy.R
import com.example.soundfy.ui.navigation.Routes
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentRoute: String,
    avatarUri: Uri?
) {
    val encodedAvatar = URLEncoder.encode(avatarUri?.toString() ?: "", StandardCharsets.UTF_8.toString())
    val context = LocalContext.current

    NavigationBar(containerColor = Color.Black) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_disc),
                    contentDescription = context.getString(R.string.artist)
                )
            },
            label = { Text(text = context.getString(R.string.artist)) },
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
                    contentDescription = context.getString(R.string.playlists)
                )
            },
            label = { context.getString(R.string.playlists) },
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
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = context.getString(R.string.profile)
                )
            },
            label = { Text(text = context.getString(R.string.profile)) },
            selected = currentRoute == Routes.PROFILE,
            onClick = {
                navController.navigate(Routes.PROFILE) {
                    popUpTo(Routes.HOME) { inclusive = false }
                }
            }
        )
    }
}
