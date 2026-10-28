import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../navigation/app_routes.dart';
import '../viewmodels/mahasiswa_viewmodel.dart';

class ProfilScreen extends StatefulWidget {
  const ProfilScreen({super.key});

  @override
  State<ProfilScreen> createState() => _ProfilScreenState();
}

class _ProfilScreenState extends State<ProfilScreen> {
  late final TextEditingController _namaController;
  late final TextEditingController _nimController;
  late final TextEditingController _prodiController;
  late final TextEditingController _semesterController;
  bool _pesanTersimpan = false;

  @override
  void initState() {
    super.initState();
    final state = context.read<MahasiswaViewModel>().state;
    _namaController = TextEditingController(text: state.nama);
    _nimController = TextEditingController(text: state.nim);
    _prodiController = TextEditingController(text: state.programStudi);
    _semesterController = TextEditingController(text: state.semester);
  }

  @override
  void dispose() {
    _namaController.dispose();
    _nimController.dispose();
    _prodiController.dispose();
    _semesterController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final viewModel = context.watch<MahasiswaViewModel>();
    final state = viewModel.state;

    return Scaffold(
      appBar: AppBar(
        title: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Profil Mahasiswa',
              style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.white,
              ),
            ),
            const Text(
              'Route: profil',
              style: TextStyle(
                fontSize: 12,
                color: Colors.white70,
              ),
            ),
          ],
        ),
        backgroundColor: const Color(0xFF0D47A1),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back, color: Colors.white),
          onPressed: () => Navigator.pop(context),
        ),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            // Kartu Data Aktif di ViewModel
            Card(
              elevation: 0,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              color: const Color(0xFFEDE7F6),
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'Data Tersimpan di ViewModel Saat Ini:',
                      style: TextStyle(
                        fontSize: 14,
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF1E293B),
                      ),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      'Nama: ${state.nama}',
                      style: const TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.w600,
                        color: Color(0xFF1E293B),
                      ),
                    ),
                    const SizedBox(height: 4),
                    Text('NIM: ${state.nim}'),
                    const SizedBox(height: 2),
                    Text('Program Studi: ${state.programStudi}'),
                    const SizedBox(height: 2),
                    Text('Fakultas: ${state.fakultas}'),
                    const SizedBox(height: 2),
                    Text('Semester: ${state.semester}'),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),

            // Form Edit Data ke ViewModel
            Card(
              elevation: 2,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              color: Colors.white,
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'Ubah Data Profil (ViewModel State)',
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF1E293B),
                      ),
                    ),
                    const SizedBox(height: 14),

                    TextField(
                      controller: _namaController,
                      decoration: const InputDecoration(
                        labelText: 'Nama Lengkap Mahasiswa',
                        border: OutlineInputBorder(),
                      ),
                      onChanged: (_) {
                        if (_pesanTersimpan) {
                          setState(() => _pesanTersimpan = false);
                        }
                      },
                    ),
                    const SizedBox(height: 12),

                    TextField(
                      controller: _nimController,
                      decoration: const InputDecoration(
                        labelText: 'NIM',
                        border: OutlineInputBorder(),
                      ),
                      onChanged: (_) {
                        if (_pesanTersimpan) {
                          setState(() => _pesanTersimpan = false);
                        }
                      },
                    ),
                    const SizedBox(height: 12),

                    TextField(
                      controller: _prodiController,
                      decoration: const InputDecoration(
                        labelText: 'Program Studi',
                        border: OutlineInputBorder(),
                      ),
                      onChanged: (_) {
                        if (_pesanTersimpan) {
                          setState(() => _pesanTersimpan = false);
                        }
                      },
                    ),
                    const SizedBox(height: 12),

                    TextField(
                      controller: _semesterController,
                      decoration: const InputDecoration(
                        labelText: 'Semester',
                        border: OutlineInputBorder(),
                      ),
                      onChanged: (_) {
                        if (_pesanTersimpan) {
                          setState(() => _pesanTersimpan = false);
                        }
                      },
                    ),
                    const SizedBox(height: 14),

                    if (_pesanTersimpan) ...[
                      const Row(
                        children: [
                          Icon(Icons.check_circle, color: Color(0xFF0D47A1), size: 18),
                          SizedBox(width: 8),
                          Expanded(
                            child: Text(
                              'Profil berhasil diperbarui di ViewModel! Data tetap aman saat rotasi layar atau pindah halaman.',
                              style: TextStyle(
                                fontSize: 12,
                                fontWeight: FontWeight.w600,
                                color: Color(0xFF0D47A1),
                              ),
                            ),
                          ),
                        ],
                      ),
                      const SizedBox(height: 14),
                    ],

                    SizedBox(
                      width: double.infinity,
                      child: ElevatedButton.icon(
                        onPressed: () {
                          viewModel.updateProfil(
                            nama: _namaController.text,
                            nim: _nimController.text,
                            prodi: _prodiController.text,
                            semester: _semesterController.text,
                          );
                          setState(() => _pesanTersimpan = true);
                        },
                        style: ElevatedButton.styleFrom(
                          backgroundColor: const Color(0xFF0D47A1),
                          foregroundColor: Colors.white,
                          padding: const EdgeInsets.symmetric(vertical: 14),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(28),
                          ),
                        ),
                        icon: const Icon(Icons.save),
                        label: const Text(
                          'Simpan ke ViewModel',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 20),

            // Tombol Navigasi Lanjutan
            Row(
              children: [
                Expanded(
                  child: OutlinedButton.icon(
                    onPressed: () {
                      Navigator.pushNamed(context, AppRoutes.detailMataKuliah);
                    },
                    style: OutlinedButton.styleFrom(
                      foregroundColor: const Color(0xFF0D47A1),
                      padding: const EdgeInsets.symmetric(vertical: 12),
                    ),
                    icon: const Icon(Icons.book, size: 18),
                    label: const Text('Mata Kuliah'),
                  ),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: OutlinedButton.icon(
                    onPressed: () {
                      Navigator.pushNamed(context, AppRoutes.kontak);
                    },
                    style: OutlinedButton.styleFrom(
                      foregroundColor: const Color(0xFF0D47A1),
                      padding: const EdgeInsets.symmetric(vertical: 12),
                    ),
                    icon: const Icon(Icons.contact_mail, size: 18),
                    label: const Text('Kontak'),
                  ),
                ),
              ],
            ),
            const SizedBox(height: 10),

            OutlinedButton.icon(
              onPressed: () => Navigator.pop(context),
              style: OutlinedButton.styleFrom(
                foregroundColor: const Color(0xFF0D47A1),
                padding: const EdgeInsets.symmetric(vertical: 14),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(28),
                ),
              ),
              icon: const Icon(Icons.arrow_back),
              label: const Text('Kembali ke Layar Sebelumnya (Back Stack)'),
            ),
          ],
        ),
      ),
    );
  }
}
