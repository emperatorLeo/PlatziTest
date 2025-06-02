package com.example.details.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.details.R

private val CircularFontFamily = FontFamily(Font(R.font.circularstd_medium),Font(R.font.circularstd_black), Font(R.font.circularstd_bold))
val CircularTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = CircularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = Font20sp,
        lineHeight = 28.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = CircularFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = Font15sp,
        lineHeight = 28.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = CircularFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = Font10sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = CircularFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = Font15sp,
        lineHeight = 28.sp),

        bodyMedium = TextStyle(
        fontFamily = CircularFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = Font12sp,
        lineHeight = 28.sp
    ),
)
