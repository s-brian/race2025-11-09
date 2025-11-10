package tw.edu.pu.csim.tcyang.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.delay

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {
    var circleX by remember { mutableStateOf(0f) }
    val circleY = 200f
    val circleRadius = 50f

    LaunchedEffect(Unit) {
        while (true) {
            circleX += 10f
            if (circleX + circleRadius >= gameViewModel.screenWidthPx) {
                gameViewModel.addScore()
                circleX = 0f
            }
            delay(50)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color.Red,
                radius = circleRadius,
                center = Offset(circleX, circleY)
            )
        }

        Text(
            text = "$message\nScore: ${gameViewModel.score}",
            color = Color.Black,
            modifier = Modifier
        )
    }
}
