package com.um.profilmahasiswa.navigation

/**
 * Definisi Route untuk setiap Destination pada aplikasi Profil Mahasiswa.
 * Prinsip: Rute diberi nama sederhana, eksplisit, dan mudah ditelusuri.
 */
sealed class Screen(val route: String, val title: String) {
    data object Beranda : Screen(route = "beranda", title = "Beranda")
    data object Profil : Screen(route = "profil", title = "Profil Mahasiswa")
    data object DetailMataKuliah : Screen(route = "detail_mata_kuliah", title = "Detail Mata Kuliah")
    data object Kontak : Screen(route = "kontak", title = "Kontak Mahasiswa")
}
