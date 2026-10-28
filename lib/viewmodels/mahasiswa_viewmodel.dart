import 'package:flutter/material.dart';
import '../models/mahasiswa_model.dart';
import '../models/mata_kuliah_model.dart';

/// ViewModel sebagai State Holder untuk Aplikasi Profil Mahasiswa pada Flutter.
/// Menggunakan ChangeNotifier sebagai implementasi ViewModel pattern.
/// Menyimpan data sehingga state tetap utuh saat navigasi antar-layar maupun rotasi layar.
class MahasiswaViewModel extends ChangeNotifier {
  MahasiswaModel _state = const MahasiswaModel();

  MahasiswaModel get state => _state;

  MataKuliah get mataKuliahTerpilih {
    return _state.daftarMataKuliah.firstWhere(
      (mk) => mk.kode == _state.mataKuliahTerpilihKode,
      orElse: () => _state.daftarMataKuliah.first,
    );
  }

  int get totalSks {
    return _state.daftarMataKuliah.fold(0, (sum, mk) => sum + mk.sks);
  }

  /// Memperbarui data profil utama mahasiswa
  void updateProfil({
    required String nama,
    required String nim,
    required String prodi,
    required String semester,
  }) {
    _state = _state.copyWith(
      nama: nama.trim().isNotEmpty ? nama.trim() : _state.nama,
      nim: nim.trim().isNotEmpty ? nim.trim() : _state.nim,
      programStudi: prodi.trim().isNotEmpty ? prodi.trim() : _state.programStudi,
      semester: semester.trim().isNotEmpty ? semester.trim() : _state.semester,
    );
    notifyListeners();
  }

  /// Memperbarui data kontak mahasiswa
  void updateKontak({
    required String email,
    required String telepon,
    required String github,
    required String instagram,
    required String alamat,
  }) {
    _state = _state.copyWith(
      email: email.trim().isNotEmpty ? email.trim() : _state.email,
      telepon: telepon.trim().isNotEmpty ? telepon.trim() : _state.telepon,
      github: github.trim().isNotEmpty ? github.trim() : _state.github,
      instagram: instagram.trim().isNotEmpty ? instagram.trim() : _state.instagram,
      alamat: alamat.trim().isNotEmpty ? alamat.trim() : _state.alamat,
    );
    notifyListeners();
  }

  /// Memilih mata kuliah untuk melihat rincian detailnya
  void pilihMataKuliah(String kode) {
    if (_state.mataKuliahTerpilihKode != kode) {
      _state = _state.copyWith(mataKuliahTerpilihKode: kode);
      notifyListeners();
    }
  }
}
