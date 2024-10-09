import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.carousel.ui.screen.ListOfComponent

@ExperimentalMaterial3Api
@Preview
@Composable
fun ListOfComponentPreview() {
    ListOfComponent(navController = rememberNavController())
}