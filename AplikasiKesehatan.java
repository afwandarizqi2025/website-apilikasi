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
