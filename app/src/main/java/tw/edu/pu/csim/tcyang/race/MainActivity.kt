package tw.edu.pu.csim.tcyang.race

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.race.ui.theme.RaceTheme
import android.content.pm.ActivityInfo
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.viewModels
import androidx.window.layout.WindowMetricsCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

        WindowCompat.setDecorFitsSystemWindows(
            window, false
        )

        val windowMetricsCalculator =
            WindowMetricsCalculator.getOrCreate()

        val currentWindowMetrics =
            windowMetricsCalculator.computeCurrentWindowMetrics(this)

        val bounds = currentWindowMetrics.bounds

        val screenWidthPx = bounds.width().toFloat()
        val screenHeightPx = bounds.height().toFloat()

        val gameViewModel: GameViewModel by viewModels()
        gameViewModel.SetGameSize(screenWidthPx,screenHeightPx)

        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        setContent {
           RaceTheme {
                GameScreen(message = "賽馬遊戲 (作者：Brian Shih)",gameViewModel)
            }
        }
    }
}
