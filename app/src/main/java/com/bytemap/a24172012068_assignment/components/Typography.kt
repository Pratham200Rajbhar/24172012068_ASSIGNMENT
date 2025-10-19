package com.bytemap.a24172012068_assignment.components

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bytemap.a24172012068_assignment.R

val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.SemiBold)
)

val interFontFamily = FontFamily(
    Font(R.font.inter_28pt_regular),
    Font(R.font.inter_28pt_bold, FontWeight.Bold),
    Font(R.font.inter_28pt_medium, FontWeight.Medium),
    Font(R.font.inter_28pt_semibold, FontWeight.SemiBold)
)

val outfitFontFamily = FontFamily(
    Font(R.font.outfit_regular),
    Font(R.font.outfit_bold, FontWeight.Bold),
    Font(R.font.outfit_semibold, FontWeight.SemiBold)
)

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_semibold, FontWeight.SemiBold)
)
