package com.example.platzitest.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.platzitest.common.EXAMPLE_AUDIO_URI
import com.example.platzitest.domain.dtos.SoundDetailsDto
import com.example.platzitest.presentation.state.UiState
import com.example.platzitest.presentation.theme.DarkBlue
import com.example.platzitest.presentation.theme.Dimen10dp
import com.example.platzitest.presentation.theme.Font15sp
import com.example.platzitest.presentation.theme.LightBlue
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun DetailScreen(uiState: State<UiState>, goBack: () -> Unit) {

    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    var mediaSource by remember { mutableStateOf(MediaItem.fromUri(EXAMPLE_AUDIO_URI)) }

    when (uiState.value) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightBlue)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    color = Color.White,
                )
            }
        }

        is UiState.Success<*> -> {
            val soundDetailsDto = (uiState.value as UiState.Success<*>).data

            if (soundDetailsDto is SoundDetailsDto) {
                mediaSource = MediaItem.fromUri(soundDetailsDto.previewMp3)
                LaunchedEffect(mediaSource) {
                    exoPlayer.setMediaItem(mediaSource)
                    exoPlayer.prepare()
                }
                SuccessDetailScreen(soundDetailsDto, exoPlayer, goBack)
            }
        }

        else -> {}
    }
}

@Composable
fun SuccessDetailScreen(
    soundDetailsDto: SoundDetailsDto,
    exoPlayer: ExoPlayer,
    goBack: () -> Unit
) {
    val lottieController = remember{ DotLottieController() }

    exoPlayer.addListener(object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            if (isPlaying) {
                lottieController.play()
            } else {
                lottieController.pause()
            }
        }
    })

    Column(
        Modifier
            .fillMaxSize()
            .background(LightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
            colorFilter = ColorFilter.tint(
                Color.White
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 50.dp)
                .size(30.dp)
                .clickable {
                    exoPlayer.stop()
                    exoPlayer.release()
                    goBack()
                }
        )

        Text(
            text = "ID: ${soundDetailsDto.id}",
            style = TextStyle(
                fontSize = Font15sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp)
                .align(Alignment.Start)
        )

        Text(
            text = soundDetailsDto.name,
            style = TextStyle(fontSize = 20.sp, color = DarkBlue),
            modifier = Modifier.padding(top = 10.dp)
        )

        Text(
            text = AnnotatedString.Companion.fromHtml(soundDetailsDto.description),
            style = TextStyle(fontSize = Font15sp, color = DarkBlue),
            modifier = Modifier
                .padding(Dimen10dp)
                .height(100.dp)
                .verticalScroll(rememberScrollState())
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
                .padding(start = Dimen10dp, end = Dimen10dp, bottom = 20.dp)
        )

        DotLottieAnimation(
            source = DotLottieSource.Url("https://lottie.host/52b78870-4b70-46a5-b4f8-b6aed5225c14/rui8qZZLyK.lottie"),
            autoplay = false,
            controller = lottieController
        )
    }
}
