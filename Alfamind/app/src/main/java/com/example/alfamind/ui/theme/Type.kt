package com.example.alfamind.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.alfamind.R

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_medium, FontWeight.Medium)
)

//val displayLarge: TextStyle = TypographyTokens.DisplayLarge,
//val displayMedium: TextStyle = TypographyTokens.DisplayMedium,
//val displaySmall: TextStyle = TypographyTokens.DisplaySmall,
//val headlineLarge: TextStyle = TypographyTokens.HeadlineLarge,
//val headlineMedium: TextStyle = TypographyTokens.HeadlineMedium,
//val headlineSmall: TextStyle = TypographyTokens.HeadlineSmall,
//val titleLarge: TextStyle = TypographyTokens.TitleLarge,
//val titleMedium: TextStyle = TypographyTokens.TitleMedium,
//val titleSmall: TextStyle = TypographyTokens.TitleSmall,
//val bodyLarge: TextStyle = TypographyTokens.BodyLarge,
//val bodyMedium: TextStyle = TypographyTokens.BodyMedium,
//val bodySmall: TextStyle = TypographyTokens.BodySmall,
//val labelLarge: TextStyle = TypographyTokens.LabelLarge,
//val labelMedium: TextStyle = TypographyTokens.LabelMedium,
//val labelSmall: TextStyle = TypographyTokens.LabelSmall,

// Set of Material typography styles to start with
val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.1.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.1.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.ExtraBold
    ),
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)