package com.example.details.presentation.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.datasource.domain.dtos.SoundDetailsDto
import com.example.details.R
import com.example.details.common.EXAMPLE_AUDIO_URI
import com.example.details.common.LOTTIE_URI
import com.example.details.presentation.state.UiState
import com.example.details.presentation.theme.DarkPurple
import com.example.details.presentation.theme.Dimen100dp
import com.example.details.presentation.theme.Dimen10dp
import com.example.details.presentation.theme.Dimen200dp
import com.example.details.presentation.theme.Dimen20dp
import com.example.details.presentation.theme.Dimen30dp
import com.example.details.presentation.theme.Dimen50dp
import com.example.details.presentation.theme.Font15sp
import com.example.details.presentation.theme.Font20sp
import com.lottiefiles.dotlottie.core.compose.runtime.DotLottieController
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun DetailScreen(uiState: State<UiState>, exoPlayer: ExoPlayer, goBack: () -> Unit) {
    var mediaSource by remember { mutableStateOf(MediaItem.fromUri(EXAMPLE_AUDIO_URI)) }

    when (uiState.value) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkPurple)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(Dimen100dp)
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

        else -> {
            ErrorState()
        }
    }
}

@Composable
fun SuccessDetailScreen(
    soundDetailsDto: SoundDetailsDto,
    exoPlayer: ExoPlayer,
    goBack: () -> Unit
) {
    val lottieController = remember { DotLottieController() }

    DisposableEffect(exoPlayer, lottieController) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (isPlaying) {
                    lottieController.play()
                } else {
                    lottieController.pause()
                }

            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(DarkPurple),
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
                .padding(start = Dimen20dp, top = Dimen50dp)
                .size(Dimen30dp)
                .clickable {
                    exoPlayer.playWhenReady = false
                    exoPlayer.stop()
                    goBack()
                }
        )

        Text(
            text = stringResource(R.string.id_label, soundDetailsDto.id),
            style = TextStyle(
                fontSize = Font15sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = Dimen10dp, start = Dimen20dp)
                .align(Alignment.Start)
        )

        Text(
            text = soundDetailsDto.name,
            style = TextStyle(fontSize = Font20sp, color = Color.White),
            modifier = Modifier.padding(top = Dimen10dp)
        )

        Text(
            text = AnnotatedString.Companion.fromHtml(soundDetailsDto.description),
            style = TextStyle(fontSize = Font15sp, color = Color.White),
            modifier = Modifier
                .padding(Dimen10dp)
                .height(Dimen100dp)
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
                .height(Dimen200dp)
                .padding(start = Dimen10dp, end = Dimen10dp, bottom = Dimen20dp)
        )

        DotLottieAnimation(
            source = DotLottieSource.Url(LOTTIE_URI),
            autoplay = false,
            controller = lottieController,
            loop = true
        )
    }
}
