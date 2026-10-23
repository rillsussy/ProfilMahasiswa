package com.um.profilmahasiswa.viewmodel

import androidx.lifecycle.ViewModel
import com.um.profilmahasiswa.model.MahasiswaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel sebagai State Holder untuk Aplikasi Profil Mahasiswa.
 * Menyimpan UI State sehingga data tetap aman meskipun layar dirotasi (configuration change)
 * atau pengguna berpindah antar-layar.
 */
class MahasiswaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MahasiswaUiState())
    val uiState: StateFlow<MahasiswaUiState> = _uiState.asStateFlow()

    /**
     * Memperbarui data profil utama (Nama, NIM, Program Studi, Semester).
     */
    fun updateProfil(
        namaBaru: String,
        nimBaru: String,
        prodiBaru: String,
        semesterBaru: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                nama = namaBaru.ifBlank { currentState.nama },
                nim = nimBaru.ifBlank { currentState.nim },
                programStudi = prodiBaru.ifBlank { currentState.programStudi },
                semester = semesterBaru.ifBlank { currentState.semester }
            )
        }
    }

    /**
     * Memperbarui data kontak mahasiswa pada layar Kontak.
     */
    fun updateKontak(
        emailBaru: String,
        teleponBaru: String,
        githubBaru: String,
        instagramBaru: String,
        alamatBaru: String
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                email = emailBaru.ifBlank { currentState.email },
                telepon = teleponBaru.ifBlank { currentState.telepon },
                github = githubBaru.ifBlank { currentState.github },
                instagram = instagramBaru.ifBlank { currentState.instagram },
                alamat = alamatBaru.ifBlank { currentState.alamat }
            )
        }
    }

    /**
     * Memilih mata kuliah untuk melihat detailnya.
     */
    fun pilihMataKuliah(kode: String) {
        _uiState.update { currentState ->
            currentState.copy(mataKuliahTerpilihKode = kode)
        }
    }
}
