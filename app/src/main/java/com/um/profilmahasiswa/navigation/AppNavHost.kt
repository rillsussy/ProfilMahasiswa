package com.um.profilmahasiswa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.um.profilmahasiswa.ui.screens.BerandaScreen
import com.um.profilmahasiswa.ui.screens.DetailMataKuliahScreen
import com.um.profilmahasiswa.ui.screens.KontakScreen
import com.um.profilmahasiswa.ui.screens.ProfilScreen
import com.um.profilmahasiswa.viewmodel.MahasiswaViewModel

/**
 * Navigation Host utama aplikasi yang menghubungkan 4 layar:
 * 1. Beranda ("beranda")
 * 2. Profil ("profil")
 * 3. Detail Mata Kuliah ("detail_mata_kuliah")
 * 4. Kontak ("kontak") - Layar tambahan Tugas Mandiri
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    mahasiswaViewModel: MahasiswaViewModel = viewModel()
) {
    val uiState by mahasiswaViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.Beranda.route,
        modifier = modifier
    ) {
        // 1. Layar Beranda
        composable(route = Screen.Beranda.route) {
            BerandaScreen(
                uiState = uiState,
                onNavigateToProfil = {
                    navController.navigate(Screen.Profil.route)
                },
                onNavigateToMataKuliah = {
                    navController.navigate(Screen.DetailMataKuliah.route)
                },
                onNavigateToKontak = {
                    navController.navigate(Screen.Kontak.route)
                }
            )
        }

        // 2. Layar Profil Mahasiswa
        composable(route = Screen.Profil.route) {
            ProfilScreen(
                uiState = uiState,
                onSimpanProfil = { nama, nim, prodi, semester ->
                    mahasiswaViewModel.updateProfil(nama, nim, prodi, semester)
                },
                onNavigateToMataKuliah = {
                    navController.navigate(Screen.DetailMataKuliah.route)
                },
                onNavigateToKontak = {
                    navController.navigate(Screen.Kontak.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 3. Layar Detail Mata Kuliah
        composable(route = Screen.DetailMataKuliah.route) {
            DetailMataKuliahScreen(
                uiState = uiState,
                onPilihMataKuliah = { kode ->
                    mahasiswaViewModel.pilihMataKuliah(kode)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Layar Kontak Mahasiswa (Tugas Tambahan 1 Layar Baru)
        composable(route = Screen.Kontak.route) {
            KontakScreen(
                uiState = uiState,
                onSimpanKontak = { email, telepon, github, instagram, alamat ->
                    mahasiswaViewModel.updateKontak(email, telepon, github, instagram, alamat)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
