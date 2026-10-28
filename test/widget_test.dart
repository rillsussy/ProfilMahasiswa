import 'package:flutter_test/flutter_test.dart';
import 'package:provider/provider.dart';
import 'package:profil_mahasiswa_flutter/main.dart';
import 'package:profil_mahasiswa_flutter/viewmodels/mahasiswa_viewmodel.dart';

void main() {
  testWidgets('Aplikasi Profil Mahasiswa loads Beranda successfully',
      (WidgetTester tester) async {
    await tester.pumpWidget(
      ChangeNotifierProvider(
        create: (_) => MahasiswaViewModel(),
        child: const ProfilMahasiswaApp(),
      ),
    );

    expect(find.text('Portal Profil Mahasiswa'), findsOneWidget);
    expect(find.text('Ryanda Hermawan'), findsOneWidget);
    expect(find.text('NIM: 2411068'), findsOneWidget);
  });
}
