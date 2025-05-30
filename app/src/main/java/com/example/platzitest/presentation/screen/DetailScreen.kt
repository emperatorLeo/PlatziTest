package com.example.platzitest.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.platzitest.common.EXAMPLE_AUDIO_URI
import com.example.platzitest.domain.dtos.SoundDetailsDto
import com.example.platzitest.presentation.theme.DarkBlue
import com.example.platzitest.presentation.theme.Font12sp
import com.example.platzitest.presentation.theme.Font15sp
import com.example.platzitest.presentation.theme.LightBlue

@Composable
fun DetailScreen(soundItem: State<SoundDetailsDto>) {

    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()

    val mediaSource = remember(EXAMPLE_AUDIO_URI) {
        MediaItem.fromUri(soundItem.value.previewMp3)
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(LightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ID: ${soundItem.value.id}",
            style = TextStyle(fontSize = Font12sp, color = Color.Black),
            modifier = Modifier
                .padding(top = 100.dp, start = 40.dp)
                .align(Alignment.Start)
        )

        Text(
            text = soundItem.value.name,
            style = TextStyle(fontSize = 20.sp, color = DarkBlue),
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = AnnotatedString.Companion.fromHtml(soundItem.value.description),
            style = TextStyle(fontSize = Font15sp, color = DarkBlue),
            modifier = Modifier.padding(top = 20.dp)
        )

        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)

        )
    }
}
