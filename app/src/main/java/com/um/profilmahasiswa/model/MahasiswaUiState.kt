package com.um.profilmahasiswa.model

data class MataKuliah(
    val kode: String,
    val nama: String,
    val sks: Int,
    val dosen: String,
    val jadwal: String,
    val deskripsi: String
)

data class MahasiswaUiState(
    // Data Profil Mahasiswa
    val nama: String = "Ryanda Hermawan",
    val nim: String = "2411068",
    val programStudi: String = "Informatika",
    val fakultas: String = "Fakultas Ilmu Komputer",
    val semester: String = "5 (Ganjil)",

    // Data Kontak Mahasiswa (Layar Tambahan Tugas)
    val email: String = "2411068@students.universitasmulia.ac.id",
    val telepon: String = "+62 895704172156",
    val github: String = "github.com/rillsussy",
    val instagram: String = "@75ryand",
    val alamat: String = "Balikpapan, Kalimantan Timur",

    // Data Mata Kuliah
    val daftarMataKuliah: List<MataKuliah> = listOf(
        MataKuliah(
            kode = "IF501",
            nama = "Pemrograman Aplikasi Bergerak",
            sks = 3,
            dosen = "Dosen Pengampu PAB, M.Kom.",
            jadwal = "Senin, 08:00 - 10:30 WITA",
            deskripsi = "Mempelajari pengembangan aplikasi Android modern dengan Jetpack Compose, Navigation, Lifecycle, dan ViewModel."
        ),
        MataKuliah(
            kode = "IF502",
            nama = "Kecerdasan Buatan",
            sks = 3,
            dosen = "Dr. Hendra Wijaya, M.Cs.",
            jadwal = "Selasa, 10:30 - 13:00 WITA",
            deskripsi = "Konsep dasar machine learning, pencarian heuristik, dan pemodelan cerdas."
        ),
        MataKuliah(
            kode = "IF503",
            nama = "Rekayasa Perangkat Lunak",
            sks = 3,
            dosen = "Siti Aminah, S.Kom., M.T.",
            jadwal = "Rabu, 13:30 - 16:00 WITA",
            deskripsi = "Metodologi pengembangan perangkat lunak Agile/Scrum, analisis kebutuhan, dan pengujian."
        ),
        MataKuliah(
            kode = "IF504",
            nama = "Keamanan Jaringan & Siber",
            sks = 3,
            dosen = "Rizky Pratama, M.Kom.",
            jadwal = "Kamis, 08:00 - 10:30 WITA",
            deskripsi = "Prinsip kriptografi, keamanan infrastruktur jaringan, dan analisis kerentanan."
        )
    ),
    val mataKuliahTerpilihKode: String = "IF501"
)
