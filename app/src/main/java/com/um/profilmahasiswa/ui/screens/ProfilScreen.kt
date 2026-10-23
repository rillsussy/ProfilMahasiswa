package com.um.profilmahasiswa.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.um.profilmahasiswa.model.MahasiswaUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilScreen(
    uiState: MahasiswaUiState,
    onSimpanProfil: (String, String, String, String) -> Unit,
    onNavigateToMataKuliah: () -> Unit,
    onNavigateToKontak: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State input form menggunakan rememberSaveable agar saat mengetik dan layar dirotasi tetap aman,
    // kemudian disimpan permanen ke MahasiswaViewModel saat tombol Simpan ditekan.
    var namaInput by rememberSaveable(uiState.nama) { mutableStateOf(uiState.nama) }
    var nimInput by rememberSaveable(uiState.nim) { mutableStateOf(uiState.nim) }
    var prodiInput by rememberSaveable(uiState.programStudi) { mutableStateOf(uiState.programStudi) }
    var semesterInput by rememberSaveable(uiState.semester) { mutableStateOf(uiState.semester) }
    var pesanTersimpan by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Profil Mahasiswa",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Route: profil",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Kartu Tampilan Data Aktif di ViewModel
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Data Tersimpan di ViewModel Saat Ini:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Nama: ${uiState.nama}",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "NIM: ${uiState.nim}")
                    Text(text = "Program Studi: ${uiState.programStudi}")
                    Text(text = "Fakultas: ${uiState.fakultas}")
                    Text(text = "Semester: ${uiState.semester}")
                }
            }

            // Form Edit Data ke ViewModel
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Ubah Data Profil (ViewModel State)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = namaInput,
                        onValueChange = {
                            namaInput = it
                            pesanTersimpan = false
                        },
                        label = { Text("Nama Lengkap Mahasiswa") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = nimInput,
                        onValueChange = {
                            nimInput = it
                            pesanTersimpan = false
                        },
                        label = { Text("NIM") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = prodiInput,
                        onValueChange = {
                            prodiInput = it
                            pesanTersimpan = false
                        },
                        label = { Text("Program Studi") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = semesterInput,
                        onValueChange = {
                            semesterInput = it
                            pesanTersimpan = false
                        },
                        label = { Text("Semester") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (pesanTersimpan) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Profil berhasil diperbarui di ViewModel! Silakan uji rotasi layar atau kembali ke Beranda.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onSimpanProfil(namaInput, nimInput, prodiInput, semesterInput)
                            pesanTersimpan = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Simpan ke ViewModel")
                    }
                }
            }

            HorizontalDivider()

            // Navigasi Lanjutan dari Layar Profil
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateToMataKuliah,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Book, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Mata Kuliah")
                }

                OutlinedButton(
                    onClick = onNavigateToKontak,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.ContactMail, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Kontak")
                }
            }

            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Kembali ke Layar Sebelumnya (Back Stack)")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
