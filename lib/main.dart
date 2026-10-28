import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'navigation/app_routes.dart';
import 'screens/beranda_screen.dart';
import 'screens/detail_mata_kuliah_screen.dart';
import 'screens/kontak_screen.dart';
import 'screens/profil_screen.dart';
import 'viewmodels/mahasiswa_viewmodel.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => MahasiswaViewModel(),
      child: const ProfilMahasiswaApp(),
    ),
  );
}

class ProfilMahasiswaApp extends StatelessWidget {
  const ProfilMahasiswaApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Profil Mahasiswa UM',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF0D47A1),
          primary: const Color(0xFF0D47A1),
          secondary: const Color(0xFFB71C1C),
          surface: Colors.white,
        ),
        scaffoldBackgroundColor: const Color(0xFFF8F9FA),
        appBarTheme: const AppBarTheme(
          backgroundColor: Color(0xFF0D47A1),
          foregroundColor: Colors.white,
          elevation: 0,
        ),
      ),
      initialRoute: AppRoutes.beranda,
      routes: {
        AppRoutes.beranda: (context) => const BerandaScreen(),
        AppRoutes.profil: (context) => const ProfilScreen(),
        AppRoutes.detailMataKuliah: (context) => const DetailMataKuliahScreen(),
        AppRoutes.kontak: (context) => const KontakScreen(),
      },
    );
  }
}
