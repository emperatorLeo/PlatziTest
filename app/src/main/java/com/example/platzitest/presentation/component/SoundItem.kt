package com.example.platzitest.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.datasource.common.EMPTY_STRING
import com.example.datasource.domain.dtos.SoundDto
import com.example.details.presentation.theme.CircularTypography
import com.example.platzitest.R
import com.example.platzitest.presentation.theme.Dimen10dp
import com.example.platzitest.presentation.theme.Dimen200dp
import com.example.platzitest.presentation.theme.Dimen30dp
import com.example.platzitest.presentation.theme.Dimen5dp
import com.example.platzitest.presentation.theme.IntenseRed
import com.example.platzitest.presentation.theme.LightBlue
import com.example.platzitest.presentation.theme.Purple40
import com.example.platzitest.presentation.theme.Purple80
import com.example.platzitest.presentation.theme.PurpleGrey40

@Composable
fun SoundItem(
    sound: SoundDto,
    onItemClick: (Int) -> Unit,
    onDeleteItem: (SoundDto) -> Unit,
    saveChanges: (SoundDto) -> Unit
) {
    var isEditionMode by remember { mutableStateOf(false) }
    var innerSound by remember { mutableStateOf(sound) }
    var integerResource by remember { mutableIntStateOf(R.drawable.ic_launcher_foreground) }
    LaunchedEffect(Unit) {
        integerResource = imageSelector()
    }
    if (!isEditionMode) {
        ItemReadMode(innerSound, onItemClick, {
            isEditionMode = !isEditionMode
        }, onDeleteItem, integerResource) {
            innerSound = it
            saveChanges(innerSound)
        }
    } else {
        ItemEditionMode(sound, onDeleteItem, onItemClick, integerResource) {
            innerSound = it
            saveChanges(innerSound)
            isEditionMode = !isEditionMode
        }
    }
}

@Composable
private fun ItemReadMode(
    sound: SoundDto,
    onItemClick: (Int) -> Unit,
    edition: () -> Unit,
    onDeleteItem: (SoundDto) -> Unit,
    integerResource: Int,
    saveChanges: (SoundDto) -> Unit
) {
    var innerLike by remember { mutableStateOf(sound.like) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(horizontal = Dimen5dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(integerResource),
                "",
                Modifier
                    .size(Dimen30dp)
                    .padding(end = Dimen5dp)
            )

            Column(
                modifier = Modifier
                    .width(width = Dimen200dp)
                    .padding(start = Dimen10dp)
                    .wrapContentHeight()
                    .clickable { onItemClick(sound.id) }) {

                Text(
                    modifier = Modifier.padding(top = Dimen10dp),
                    text = stringResource(R.string.id_label, sound.id),
                    color = LightBlue,
                    style = CircularTypography.headlineSmall
                )

                Text(
                    modifier = Modifier.padding(top = Dimen10dp),
                    text = sound.name,
                    color = Color.Black,
                    style = CircularTypography.bodyLarge
                )

                Text(
                    modifier = Modifier.padding(top = Dimen10dp, bottom = Dimen10dp),
                    text = stringResource(R.string.author_label, sound.username),
                    color = Color.Black,
                    style = CircularTypography.bodyMedium
                )
            }

            Image(
                imageVector = if (innerLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                EMPTY_STRING,
                colorFilter = ColorFilter.tint(if (innerLike) Purple40 else Purple80),
                modifier = Modifier
                    .padding(end = Dimen10dp)
                    .size(Dimen30dp)
                    .clickable {
                        innerLike = !innerLike
                        saveChanges(sound.copy(like = innerLike))
                    }
            )

            Image(
                imageVector = Icons.Filled.Edit,
                EMPTY_STRING,
                colorFilter = ColorFilter.tint(PurpleGrey40),
                modifier = Modifier
                    .padding(end = Dimen10dp)
                    .size(Dimen30dp)
                    .clickable {
                        edition()
                    }
            )

            Image(
                imageVector = Icons.Filled.Delete,
                EMPTY_STRING,
                colorFilter = ColorFilter.tint(IntenseRed),
                modifier = Modifier
                    .size(Dimen30dp)
                    .clickable {
                        onDeleteItem(sound)
                    }
            )
        }
        HorizontalDivider()
    }
}

@Composable
private fun ItemEditionMode(
    sound: SoundDto,
    onDeleteItem: (SoundDto) -> Unit,
    onItemClicked: (Int) -> Unit,
    integerResource: Int,
    saveChanges: (SoundDto) -> Unit
) {
    var innerLike by remember { mutableStateOf(sound.like) }
    var innerName by remember { mutableStateOf(sound.name) }
    var innerAuthor by remember { mutableStateOf(sound.username) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(start = Dimen5dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(integerResource),
                "",
                Modifier
                    .size(Dimen30dp)
                    .padding(end = Dimen5dp)
            )

            Column(
                modifier = Modifier
                    .width(width = Dimen200dp)
                    .padding(start = Dimen10dp)
                    .wrapContentHeight()
                    .clickable {
                        onItemClicked(sound.id)
                    }
            ) {

                Text(
                    modifier = Modifier.padding(top = Dimen5dp),
                    text = stringResource(R.string.id_label, sound.id),
                    color = LightBlue,
                    style = CircularTypography.headlineSmall
                )

                TextField(
                    innerName,
                    onValueChange = {
                        innerName = it
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        focusedTextColor = Color.Black
                    ),
                    textStyle = CircularTypography.bodyLarge,
                    modifier = Modifier.focusRequester(focusRequester)
                )

                TextField(
                    innerAuthor,
                    onValueChange = {
                        innerAuthor = it
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        focusedTextColor = Color.Black
                    ),
                    textStyle = CircularTypography.bodyMedium
                )
            }

            Image(
                imageVector = if (innerLike) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                EMPTY_STRING,
                colorFilter = ColorFilter.tint(if (innerLike) Purple40 else Purple80),
                modifier = Modifier
                    .padding(end = Dimen10dp)
                    .size(Dimen30dp)
                    .clickable {
                        innerLike = !innerLike
                        saveChanges(sound.copy(like = innerLike))
                    }
            )

            Image(
                imageVector = Icons.Rounded.CheckCircle,
                EMPTY_STRING,
                colorFilter = ColorFilter.tint(Purple40),
                modifier = Modifier
                    .padding(end = Dimen10dp)
                    .size(Dimen30dp)
                    .clickable {
                        saveChanges(
                            sound.copy(
                                name = innerName,
                                username = innerAuthor,
                                like = innerLike
                            )
                        )
                    }
            )

            Image(
                imageVector = Icons.Filled.Delete,
                EMPTY_STRING,
                colorFilter = ColorFilter.tint(IntenseRed),
                modifier = Modifier
                    .size(Dimen30dp)
                    .clickable {
                        onDeleteItem(sound)
                    }
            )
        }
        HorizontalDivider()
    }
}

private fun imageSelector(): Int {
    val random = (0..4).random()

    val resources = when (random) {
        0 -> R.drawable.violin
        1 -> R.drawable.piano_de_cola
        2 -> R.drawable.speackers
        else -> R.drawable.spotify_purple
    }

    return resources
}