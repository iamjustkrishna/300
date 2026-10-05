package space.iamjustkrishna.threehundred

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import space.iamjustkrishna.threehundred.core.designsystem.SpartanTheme
import space.iamjustkrishna.threehundred.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpartanTheme {
                AppNavHost()
            }
        }
    }
}
