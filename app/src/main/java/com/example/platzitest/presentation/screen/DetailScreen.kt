package com.example.platzitest.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.ui.PlayerView
import com.example.platzitest.domain.dtos.SoundDto
import com.example.platzitest.presentation.theme.DarkBlue
import com.example.platzitest.presentation.theme.Dimen5dp
import com.example.platzitest.presentation.theme.Font12sp
import com.example.platzitest.presentation.theme.Font15sp
import com.example.platzitest.presentation.theme.LightBlue

@Composable
fun DetailScreen(soundItem: State<SoundDto>, saveChanges: (SoundDto) -> Unit) {
    var innerLike by remember { mutableStateOf(soundItem.value.like) }
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val example_audio_uri = "https://cdn.freesound.org/previews/172/172650_1015240-hq.mp3"

    val mediaSource = remember(example_audio_uri) {
        MediaItem.fromUri(example_audio_uri)
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
            text = soundItem.value.username,
            style = TextStyle(fontSize = Font15sp, color = DarkBlue),
            modifier = Modifier.padding(top = 20.dp)
        )

        Image(
            imageVector = if (innerLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            "",
            colorFilter = ColorFilter.tint(if (innerLike) Color.Red else Color.Black),
            modifier = Modifier
                .padding(end = Dimen5dp)
                .size(80.dp)
                .clickable {
                    innerLike = !innerLike
                    saveChanges(soundItem.value.copy(like = innerLike))
                }
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
