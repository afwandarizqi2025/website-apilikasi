import java.util.Scanner;

// Kelas utama aplikasi
public class AplikasiKesehatan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean berjalan = true;

        System.out.println("==========================================");
        System.out.println("   APLIKASI MANAJEMEN KESAHATAN PERSONAL   ");
        System.out.println("==========================================");

        while (berjalan) {
            System.out.println("\nPilih Fitur:");
            System.out.println("1. Hitung BMI (Body Mass Index)");
            System.out.println("2. Hitung Kebutuhan Air Minum Harian");
            System.out.println("3. Keluar");
            System.out.print("Pilihan Anda (1-3): ");

            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    hitungBMI(scanner);
                    break;
                case 2:
                    hitungKebutuhanAir(scanner);
                    break;
                case 3:
                    berjalan = false;
                    System.out.println("\nTerima kasih! Tetap jaga kesehatan Anda.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }

    // Fitur 1: Hitung BMI
    private static void hitungBMI(Scanner scanner) {
        System.out.println("\n--- Fitur Kalkulator BMI ---");
        System.out.print("Masukkan berat badan (kg): ");
        double berat = scanner.nextDouble();

        System.out.print("Masukkan tinggi badan (cm): ");
        double tinggiCm = scanner.nextDouble();

        // Konversi tinggi ke meter
        double tinggiMeter = tinggiCm / 100.0;
        
        // Rumus BMI = Berat / (Tinggi * Tinggi)
        double bmi = berat / (tinggiMeter * tinggiMeter);

        System.out.printf("\nNilai BMI Anda: %.2f\n", bmi);
        System.out.print("Kategori: ");

        if (bmi < 18.5) {
            System.out.println("Kekurangan berat badan (Underweight)");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            System.out.println("Berat badan normal (Ideal)");
        } else if (bmi >= 25.0 && bmi < 29.9) {
            System.out.println("Kelebihan berat badan (Overweight)");
        } else {
            System.out.println("Obesitas");
        }
    }

    // Fitur 2: Hitung Kebutuhan Air Harian
    private static void hitungKebutuhanAir(Scanner scanner) {
        System.out.println("\n--- Fitur Kalkulator Kebutuhan Air ---");
        System.out.print("Masukkan berat badan Anda (kg): ");
        double berat = scanner.nextDouble();

        // Estimasi standar kebutuhan air: 35 ml per kg berat badan
        double airMl = berat * 35;
        double airLiter = airMl / 1000.0;

        System.out.printf("\nKebutuhan air minum harian Anda: %.2f Liter (sekitar %.0f ml)\n", airLiter, airMl);
        System.out.println("Tips: Minumlah air secara berkala sepanjang hari, bukan sekaligus banyak.");
    }
}

import java.util.Scanner;

// 1. ABSTRACT CLASS (Abstraction & Encapsulation)
abstract class Pengguna {
    private String nama;
    private double beratBadan; // dalam kg
    private double tinggiBadan; // dalam cm

    public Pengguna(String nama, double beratBadan, double tinggiBadan) {
        this.nama = nama;
        this.beratBadan = beratBadan;
        this.tinggiBadan = tinggiBadan;
    }

    // Getter dan Setter
    public String getNama() { return nama; }
    public double getBeratBadan() { return beratBadan; }
    public double getTinggiBadan() { return tinggiBadan; }

    public void setBeratBadan(double beratBadan) { this.beratBadan = beratBadan; }
    public void setTinggiBadan(double tinggiBadan) { this.tinggiBadan = tinggiBadan; }

    // Abstract method
    public abstract void tampilkanProfil();
}

// 2. SUBCLASS (Inheritance)
class Pasien extends Pengguna {
    public Pasien(String nama, double beratBadan, double tinggiBadan) {
        super(nama, beratBadan, tinggiBadan);
    }

    @Override
    public void tampilkanProfil() {
        System.out.println("\n=== PROFIL PASIEN ===");
        System.out.println("Nama         : " + getNama());
        System.out.println("Berat Badan  : " + getBeratBadan() + " kg");
        System.out.println("Tinggi Badan : " + getTinggiBadan() + " cm");
        System.out.println("=====================");
    }
}

// 3. INTERFACE (Polymorphism)
interface LayananKesehatan {
    void hitung(Pengguna pengguna);
}

// 4. IMPLEMENTASI INTERFACE 1: Kalkulator BMI
class KalkulatorBMI implements LayananKesehatan {
    @Override
    public void hitung(Pengguna pengguna) {
        double tinggiMeter = pengguna.getTinggiBadan() / 100.0;
        double bmi = pengguna.getBeratBadan() / (tinggiMeter * tinggiMeter);

        System.out.println("\n--- HASIL ANALISIS BMI ---");
        System.out.printf("Indeks Massa Tubuh (BMI) : %.2f\n", bmi);
        System.out.print("Kategori Status Gizi      : ");

        if (bmi < 18.5) {
            System.out.println("Kekurangan berat badan (Underweight)");
        } else if (bmi < 24.9) {
            System.out.println("Normal / Ideal");
        } else if (bmi < 29.9) {
            System.out.println("Kelebihan berat badan (Overweight)");
        } else {
            System.out.println("Obesitas");
        }
    }
}

// 5. IMPLEMENTASI INTERFACE 2: Kalkulator Hidrasi Harian
class KalkulatorAir implements LayananKesehatan {
    @Override
    public void hitung(Pengguna pengguna) {
        // Estimasi standar kebutuhan air: 35 ml per kg berat badan
        double airMl = pengguna.getBeratBadan() * 35;
        double airLiter = airMl / 1000.0;

        System.out.println("\n--- HASIL REKOMENDASI HIDRASI ---");
        System.out.printf("Kebutuhan Air Harian : %.2f Liter (%.0f ml)\n", airLiter, airMl);
        System.out.println("Catatan: Minum air teratur sepanjang hari untuk menjaga fokus dan stamina.");
    }
}

// 6. KELAS UTAMA (Main Launcher)
public class MainAplikasiKesehatan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   SYSTEM MANAJEMEN KESAHATAN (OOP JAVA)  ");
        System.out.println("==========================================");

        // Input data pasien
        System.out.print("Masukkan Nama Anda        : ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Berat Badan (kg) : ");
        double berat = scanner.nextDouble();
        System.out.print("Masukkan Tinggi Badan (cm): ");
        double tinggi = scanner.nextDouble();

        // Instansiasi objek Pasien
        Pengguna pasien = new Pasien(nama, berat, tinggi);

        // Menu Aplikasi
        boolean berjalan = true;
        while (berjalan) {
            pasien.tampilkanProfil();
            System.out.println("Pilih Layanan Kesehatan:");
            System.out.println("1. Analisis BMI");
            System.out.println("2. Rekomendasi Kebutuhan Air Minum");
            System.out.println("3. Jalankan Semua Analisis");
            System.out.println("4. Keluar");
            System.out.print("Pilihan (1-4): ");

            int pilihan = scanner.nextInt();
            LayananKesehatan layanan;

            switch (pilihan) {
                case 1:
                    layanan = new KalkulatorBMI();
                    layanan.hitung(pasien);
                    break;
                case 2:
                    layanan = new KalkulatorAir();
                    layanan.hitung(pasien);
                    break;
                case 3:
                    // Menunjukkan Polymorphism dengan list layanan
                    LayananKesehatan[] daftarLayanan = { new KalkulatorBMI(), new KalkulatorAir() };
                    for (LayananKesehatan l : daftarLayanan) {
                        l.hitung(pasien);
                    }
                    break;
                case 4:
                    berjalan = false;
                    System.out.println("\nTerima kasih! Tetap jaga kesehatan.");
                    break;
                default:
                    System.out.println("\nPilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
