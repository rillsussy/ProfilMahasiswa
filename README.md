# 🎓 Aplikasi Profil Mahasiswa — Multi-Screen Jetpack Compose

Tugas & Praktikum **Materi 5: Navigation, Lifecycle & ViewModel**  
Mata Kuliah: **Pemrograman Aplikasi Bergerak**  
Program Studi: **S1 Informatika** — **Universitas Mulia**  

**Pengembang:**
- **Nama:** Ryanda Hermawan
- **NIM:** 2411068
- **GitHub:** [@rillsussy](https://github.com/rillsussy)

---

## 📸 Tangkapan Layar Aplikasi (Screenshots Hasil Run)

| 1. Layar Beranda | 2. Layar Profil Mahasiswa |
| :---: | :---: |
| ![Layar Beranda](screenshots/1_beranda.png) | ![Layar Profil Mahasiswa](screenshots/2_profil.png) |
| **3. Layar Detail Mata Kuliah** | **4. Layar Kontak Mahasiswa (Tugas)** |
| ![Layar Detail Mata Kuliah](screenshots/3_detail_mata_kuliah.png) | ![Layar Kontak Mahasiswa](screenshots/4_kontak.png) |

---

## 🚀 Fitur & Alur Navigasi Antar-Layar

Aplikasi ini mengimplementasikan konsep modern Android architecture dengan pemisahan tanggung jawab (*separation of concerns*):

| Layar | Route | Deskripsi & Interaksi |
| :--- | :--- | :--- |
| **1. Beranda** | `beranda` | Halaman utama yang menampilkan ringkasan profil mahasiswa langsung dari `MahasiswaViewModel` serta tombol navigasi menuju seluruh layar. |
| **2. Profil Mahasiswa** | `profil` | Menampilkan identitas akademik dan formulir interaktif untuk memperbarui **Nama**, **NIM**, **Program Studi**, dan **Semester** yang langsung tersimpan ke `ViewModel`. |
| **3. Detail Mata Kuliah** | `detail_mata_kuliah` | Menampilkan rincian daftar mata kuliah yang diambil semester ini (SKS, dosen pengampu, jadwal, dan deskripsi). Pengguna dapat memilih mata kuliah untuk melihat detail interaktif. |
| **4. Kontak Mahasiswa *(Layar Tambahan Tugas)*** | `kontak` | Layar tugas mandiri yang memuat identitas kontak mahasiswa (**Email Kampus**, **Nomor Telepon / WhatsApp**, **GitHub**, **Media Sosial**, dan **Domisili**) serta formulir edit kontak ke `ViewModel`. |

---

## 🛠️ Arsitektur & Teknologi

- **UI Framework**: Android Jetpack Compose (Material Design 3)
- **Navigation**: `androidx.navigation:navigation-compose` dengan alur eksplisit dan pengelolaan riwayat layar (*Back Stack*).
- **State Management**: `androidx.lifecycle:lifecycle-viewmodel-compose` menggunakan `StateFlow` dan immutable data pattern.
- **Lifecycle Awareness**: UI State disimpan pada `ViewModel` yang bertahan melewati siklus hidup layar dan perubahan konfigurasi (*configuration change* seperti rotasi layar Portrait ↔ Landscape).
- **Build System**: Gradle Version Catalog (`libs.versions.toml`) & Kotlin 2.0.

---

## ✅ Checklist Keberhasilan Praktik & Tugas (Materi 5)

- [x] Aplikasi memiliki 4 layar (3 layar utama praktikum + 1 layar tugas mandiri Kontak Mahasiswa).
- [x] Setiap layar memiliki *route* yang terdefinisi secara eksplisit (`beranda`, `profil`, `detail_mata_kuliah`, `kontak`).
- [x] Navigasi berjalan mulus tanpa *crash* menggunakan `NavController.navigate()`.
- [x] Tombol kembali bekerja sesuai riwayat tumpukan layar (*Back Stack* / `popBackStack()`).
- [x] `MahasiswaViewModel` digunakan sebagai *single source of truth* untuk menjaga *UI State*.
- [x] Kode di-*commit* ke GitHub dengan pesan commit yang rapi dan deskriptif.
- [x] Tersedia file APK siap pakai pada repository.

---

## 📦 File APK
File APK siap instal tersedia di root repository:
- [`ProfilMahasiswaApp.apk`](ProfilMahasiswaApp.apk)
