import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../viewmodels/mahasiswa_viewmodel.dart';

class KontakScreen extends StatefulWidget {
  const KontakScreen({super.key});

  @override
  State<KontakScreen> createState() => _KontakScreenState();
}

class _KontakScreenState extends State<KontakScreen> {
  late final TextEditingController _emailController;
  late final TextEditingController _teleponController;
  late final TextEditingController _githubController;
  late final TextEditingController _instagramController;
  late final TextEditingController _alamatController;
  bool _pesanTersimpan = false;

  @override
  void initState() {
    super.initState();
    final state = context.read<MahasiswaViewModel>().state;
    _emailController = TextEditingController(text: state.email);
    _teleponController = TextEditingController(text: state.telepon);
    _githubController = TextEditingController(text: state.github);
    _instagramController = TextEditingController(text: state.instagram);
    _alamatController = TextEditingController(text: state.alamat);
  }

  @override
  void dispose() {
    _emailController.dispose();
    _teleponController.dispose();
    _githubController.dispose();
    _instagramController.dispose();
    _alamatController.dispose();
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
              'Kontak Mahasiswa',
              style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Colors.white,
              ),
            ),
            const Text(
              'Route: kontak (Layar Tambahan Tugas)',
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
            // Kartu Ringkasan Kontak Aktif
            Card(
              elevation: 2,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              color: const Color(0xFFEDE7F6),
              child: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Kartu Kontak • ${state.nama}',
                      style: const TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF1E293B),
                      ),
                    ),
                    const SizedBox(height: 2),
                    Text(
                      '${state.programStudi} (${state.nim})',
                      style: const TextStyle(
                        fontSize: 12,
                        color: Color(0xFF475569),
                      ),
                    ),
                    const Divider(height: 20),
                    _buildBarisKontak(
                      icon: Icons.email,
                      label: 'Email Kampus',
                      value: state.email,
                    ),
                    const SizedBox(height: 10),
                    _buildBarisKontak(
                      icon: Icons.phone,
                      label: 'Telepon / WhatsApp',
                      value: state.telepon,
                    ),
                    const SizedBox(height: 10),
                    _buildBarisKontak(
                      icon: Icons.code,
                      label: 'GitHub',
                      value: state.github,
                    ),
                    const SizedBox(height: 10),
                    _buildBarisKontak(
                      icon: Icons.alternate_email,
                      label: 'Media Sosial',
                      value: state.instagram,
                    ),
                    const SizedBox(height: 10),
                    _buildBarisKontak(
                      icon: Icons.location_on,
                      label: 'Domisili',
                      value: state.alamat,
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),

            // Form Edit Kontak di ViewModel
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
                      'Perbarui Kontak (Tersimpan di ViewModel)',
                      style: TextStyle(
                        fontSize: 16,
                        fontWeight: FontWeight.bold,
                        color: Color(0xFF1E293B),
                      ),
                    ),
                    const SizedBox(height: 14),

                    TextField(
                      controller: _emailController,
                      decoration: const InputDecoration(
                        labelText: 'Email Kampus',
                        prefixIcon: Icon(Icons.email),
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
                      controller: _teleponController,
                      decoration: const InputDecoration(
                        labelText: 'Nomor Telepon / WhatsApp',
                        prefixIcon: Icon(Icons.phone),
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
                      controller: _githubController,
                      decoration: const InputDecoration(
                        labelText: 'Username / URL GitHub',
                        prefixIcon: Icon(Icons.code),
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
                      controller: _instagramController,
                      decoration: const InputDecoration(
                        labelText: 'Instagram / LinkedIn',
                        prefixIcon: Icon(Icons.alternate_email),
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
                      controller: _alamatController,
                      decoration: const InputDecoration(
                        labelText: 'Alamat Domisili',
                        prefixIcon: Icon(Icons.location_on),
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
                              'Data kontak berhasil disimpan di ViewModel!',
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
                          viewModel.updateKontak(
                            email: _emailController.text,
                            telepon: _teleponController.text,
                            github: _githubController.text,
                            instagram: _instagramController.text,
                            alamat: _alamatController.text,
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
                          'Simpan Kontak ke ViewModel',
                          style: TextStyle(fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            const SizedBox(height: 16),

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
              label: const Text('Kembali (Back Stack)'),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildBarisKontak({
    required IconData icon,
    required String label,
    required String value,
  }) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Icon(icon, size: 20, color: const Color(0xFF0D47A1)),
        const SizedBox(width: 12),
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                label,
                style: const TextStyle(
                  fontSize: 11,
                  color: Color(0xFF64748B),
                ),
              ),
              const SizedBox(height: 2),
              Text(
                value,
                style: const TextStyle(
                  fontSize: 13,
                  fontWeight: FontWeight.w500,
                  color: Color(0xFF1E293B),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
