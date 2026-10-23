package com.um.profilmahasiswa.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = MuliaBlue,
    secondary = MuliaRed,
    tertiary = MuliaGold,
    background = BackgroundLight,
    surface = SurfaceLight
)

@Composable
fun ProfilMahasiswaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
