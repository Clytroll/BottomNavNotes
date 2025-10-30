Implementasi Pola Navigasi dan Passing Data (Jetpack Compose)

Pola Navigasi
Pada aplikasi ini digunakan pola navigasi "Bottom Navigation".  
Pola ini menampilkan menu navigasi di bagian bawah layar dan digunakan untuk berpindah antar halaman utama aplikasi.

Terdapat dua menu utama:
1. Home → Menampilkan daftar catatan yang telah dibuat.
2. Add Note → Menambahkan catatan baru melalui form input.

Navigasi diatur menggunakan "Navigation Component for Jetpack Compose" dengan memanfaatkan `NavHost` dan `NavController`.  
Setiap item pada Bottom Navigation akan menavigasi ke "Composable" yang berbeda sesuai route yang telah ditentukan di kelas `Screen`.

Contoh penerapan navigasi:
NavHost(
    navController = navController,
    startDestination = Screen.Home.route
) {
    composable(Screen.Home.route) { HomeScreen(...) }
    composable(Screen.AddNote.route) { AddNoteScreen(...) }
    composable(Screen.Detail.route) { DetailScreen(...) }
}

Pola "Bottom Navigation" dipilih karena mudah digunakan dan cocok untuk aplikasi dengan beberapa fitur utama.  
Setiap item memiliki label dan indikator aktif, sehingga pengguna dapat mengetahui halaman mana yang sedang dibuka.

Mekanisme Pengiriman Data
Mekanisme pengiriman data antar layar menggunakan "argument route" pada `NavController` dan objek data yang ditandai dengan anotasi `@Serializable`.

Objek data:
@Serializable
data class Note(
    val title: String,
    val content: String
)

Data dikirim dari "HomeScreen" ke "DetailScreen" saat pengguna memilih salah satu catatan:
navController.navigate(Screen.Detail.createRoute(note.title, note.content))

Kemudian data diterima di "DetailScreen" melalui argumen yang diambil dari `backStackEntry`:
val title = backStackEntry.arguments?.getString("title")
val content = backStackEntry.arguments?.getString("content")

Dengan cara ini, data dari satu screen (sumber) dapat dikirim ke screen lainnya (tujuan) secara aman dan efisien tanpa perlu menggunakan "ViewModel" atau "Shared Preferences*.

Kesimpulan
- Pola navigasi yang digunakan adalah "Bottom Navigation" untuk berpindah antar halaman utama.  
- Pengiriman data antar screen dilakukan menggunakan 'NavController' dengan "route arguments" dan objek '@Serializable'.  
