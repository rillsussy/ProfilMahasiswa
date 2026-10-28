import 'mata_kuliah_model.dart';

class MahasiswaModel {
  // Data Profil Mahasiswa
  final String nama;
  final String nim;
  final String programStudi;
  final String fakultas;
  final String semester;

  // Data Kontak Mahasiswa (Layar Tambahan Tugas)
  final String email;
  final String telepon;
  final String github;
  final String instagram;
  final String alamat;

  // Data Mata Kuliah
  final List<MataKuliah> daftarMataKuliah;
  final String mataKuliahTerpilihKode;

  const MahasiswaModel({
    this.nama = 'Ryanda Hermawan',
    this.nim = '2411068',
    this.programStudi = 'Informatika',
    this.fakultas = 'Fakultas Ilmu Komputer',
    this.semester = '5 (Ganjil)',
    this.email = '2411068@students.universitasmulia.ac.id',
    this.telepon = '+62 895704172156',
    this.github = 'github.com/rillsussy',
    this.instagram = '@75ryand',
    this.alamat = 'Balikpapan, Kalimantan Timur',
    this.daftarMataKuliah = const [
      MataKuliah(
        kode: 'IF501',
        nama: 'Pemrograman Aplikasi Bergerak',
        sks: 3,
        dosen: 'Dosen Pengampu PAB, M.Kom.',
        jadwal: 'Senin, 08:00 - 10:30 WITA',
        deskripsi:
            'Mempelajari pengembangan aplikasi mobile modern dengan Flutter, Navigation, Lifecycle, dan State Management / ViewModel.',
      ),
      MataKuliah(
        kode: 'IF502',
        nama: 'Kecerdasan Buatan',
        sks: 3,
        dosen: 'Dr. Hendra Wijaya, M.Cs.',
        jadwal: 'Selasa, 10:30 - 13:00 WITA',
        deskripsi:
            'Konsep dasar machine learning, pencarian heuristik, dan pemodelan cerdas.',
      ),
      MataKuliah(
        kode: 'IF503',
        nama: 'Rekayasa Perangkat Lunak',
        sks: 3,
        dosen: 'Siti Aminah, S.Kom., M.T.',
        jadwal: 'Rabu, 13:30 - 16:00 WITA',
        deskripsi:
            'Metodologi pengembangan perangkat lunak Agile/Scrum, analisis kebutuhan, dan pengujian.',
      ),
      MataKuliah(
        kode: 'IF504',
        nama: 'Keamanan Jaringan & Siber',
        sks: 3,
        dosen: 'Rizky Pratama, M.Kom.',
        jadwal: 'Kamis, 08:00 - 10:30 WITA',
        deskripsi:
            'Prinsip kriptografi, keamanan infrastruktur jaringan, dan analisis kerentanan.',
      ),
    ],
    this.mataKuliahTerpilihKode = 'IF501',
  });

  MahasiswaModel copyWith({
    String? nama,
    String? nim,
    String? programStudi,
    String? fakultas,
    String? semester,
    String? email,
    String? telepon,
    String? github,
    String? instagram,
    String? alamat,
    List<MataKuliah>? daftarMataKuliah,
    String? mataKuliahTerpilihKode,
  }) {
    return MahasiswaModel(
      nama: nama ?? this.nama,
      nim: nim ?? this.nim,
      programStudi: programStudi ?? this.programStudi,
      fakultas: fakultas ?? this.fakultas,
      semester: semester ?? this.semester,
      email: email ?? this.email,
      telepon: telepon ?? this.telepon,
      github: github ?? this.github,
      instagram: instagram ?? this.instagram,
      alamat: alamat ?? this.alamat,
      daftarMataKuliah: daftarMataKuliah ?? this.daftarMataKuliah,
      mataKuliahTerpilihKode:
          mataKuliahTerpilihKode ?? this.mataKuliahTerpilihKode,
    );
  }
}
