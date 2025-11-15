import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Menu> daftarMenu = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static final String PASSWORD_MANAJEMEN = "admin123";

    public static void main(String[] args) {
        //data awal
        daftarMenu.add(new Menu("Nasi Goreng", 25000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Goreng", 30000, "Makanan"));
        daftarMenu.add(new Menu("Soto Ayam", 20000, "Makanan"));
        daftarMenu.add(new Menu("Mie Goreng", 22000, "Makanan"));
        daftarMenu.add(new Menu("Es Teh", 5000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 7000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Hitam", 8000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 12000, "Minuman"));

        int pilihan = 0;
        String inputPilihan;

        do {
            System.out.println("\n===== APLIKASI RESTORAN =====");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Menu Manajemen");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");

            inputPilihan = input.nextLine();

            try {
                pilihan = Integer.parseInt(inputPilihan.trim());
            } catch (NumberFormatException e) {
                System.out.println("input tidak valid");
                pilihan = 0;
                continue;
            }

            switch (pilihan) {
                case 1:
                menuPelanggan();
                break;
                case 2:
                if (validasiPassword()) {
                    menuManajemen();
                }else {
                    System.out.println("Password salah");
                }
                break;
                case 3:
                System.out.println("Terima kasih telah menggunakan aplikasi restoran");
                break;
                default:
                System.out.println("pilihan tidak valid");
            }
        } while (pilihan !=3);
    }

    static boolean validasiPassword() {
    System.out.print("\n Masukan Password Manajemen: ");
    String inputPassword = input.nextLine().trim();
    return inputPassword.equals(PASSWORD_MANAJEMEN);
    }

    //menu pelanggan
    static void menuPelanggan() {
        System.out.println("\n===== DAFTAR MENU RESTORAN =====");
        tampilkanDaftarMenu();

        ArrayList<String> namaPesanan = new ArrayList<>();
        ArrayList<Integer> jumlahPesanan = new ArrayList<>();

        System.out.println("\nKetik 'selesai' jika sudah selesai memesan");
        while (true) {
            System.out.print("masukan nomor meu: ");
            String inputMenu = input.nextLine().trim();

            if (inputMenu.equalsIgnoreCase("selesai")) break;

            int nomor;
            try {
                nomor = Integer.parseInt(inputMenu);
            } catch (NumberFormatException e){
                System.out.println("input tidak validn, masukan nomor atau ketik 'selesai'.");
                continue;
            }

            if (nomor < 1 || nomor > daftarMenu.size()) {
                System.out.println("nomor menu tidak valid");
                continue;
            }

            Menu menuDipilih = cariMenuBerdasarkanNomor(nomor);

            if (menuDipilih == null) {
                System.out.println("nomor menu tidak ditekuman");
                continue;
            }

            int jumlah = 0;
            boolean inputValid = false;

            do {
                System.out.printf("%s Jumlah: ", menuDipilih.nama);
                String inputJumlah = input.nextLine().trim();

                try {
                    jumlah = Integer.parseInt(inputJumlah);
                    if (jumlah>0) {
                        inputValid = true;
                    } else {
                        System.out.println("Jumlah harus bilangan bulat positif");
                    }
                } catch (NumberFormatException e){
                    System.out.println("input tidak valid");
                }
            } while (!inputValid);

            namaPesanan.add(menuDipilih.nama);
            jumlahPesanan.add(jumlah);
        }

        hitungDanCetakStruk(namaPesanan,jumlahPesanan);
    }

    //menu manajemen
    static void menuManajemen() {
        int pilih = 0;
        String inputPilih;

        do {
            System.out.println("\n===== MENU MANAJEMEN =====");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah harga");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Daftar Menu");
            System.out.println("5. Kembalii");
            System.out.print("Pilih: ");

            inputPilih = input.nextLine();

            try {
                pilih = Integer.parseInt(inputPilih.trim());
            } catch (NumberFormatException e){
                System.out.println(" input tidak valid");
                pilih = 0;
                continue;
            }

            switch (pilih) {
                case 1:
                    tambahMenu();
                    break;
                case 2:
                    ubahHargaMenu();
                    break;
                case 3:
                    hapusMenu();
                    break;
                case 4:
                    tampilkanDaftarMenu();
                    break;
                case 5:
                    System.out.println("kembali ke menu utama");
                    break;
                default:
                    System.out.println("pilihan tidak valid");
            }
        } while (pilih !=5);
    }

    //fungsi bantu manajemen
    static void tambahMenu() {
        System.out.print("nama menu baru: ");
        String nama = input.nextLine();

        //validasi
        int harga = 0;
        boolean inputValid = false;
        do {
            System.out.print("harga");
            String inputharga = input.nextLine().trim();
            try {
                harga = Integer.parseInt(inputharga);
                if(harga>0) {
                    inputValid = true;
                } else {
                    System.out.println("harga harus bilangan bulat positif");
                }
            } catch (NumberFormatException e){
                System.out.println("input harga tidka valid");
            }
        } while (!inputValid);

        System.out.print("kategori (Makanan/Minuman): ");
        String kategori = input.nextLine();
        daftarMenu.add(new Menu(nama, harga, kategori));
        System.out.println("menu berhasil ditambah");
    }

    static void ubahHargaMenu() {
        tampilkanDaftarMenu();

        //validasi
        int nomor = 0;
        boolean inputValid = false;
        do {
            System.out.print("masukan nomor menu yang ingin diubah: ");
            String inputNomor = input.nextLine().trim();
            try {
                nomor = Integer.parseInt(inputNomor);
                if (nomor>=1&&nomor<= daftarMenu.size()) {
                    inputValid = true;
                }else {
                    System.out.println("nomor tidak valid, masukan nomor antara 1 sampai" + daftarMenu.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("input tidak valid, masukan angka");
            }
        }while (!inputValid);

        Menu menu = cariMenuBerdasarkanNomor(nomor);

        //validasi
        int hargaBaru = 0;
        inputValid = false;
        do {
            System.out.print("harga baru: ");
            String inputHarga = input.nextLine().trim();
            try {
                hargaBaru = Integer.parseInt(inputHarga);
                if (hargaBaru>0) {
                    inputValid = true;
                } else {
                    System.out.println("harga harus bilangan bulat positif");
                }
            } catch (NumberFormatException e){
                System.out.println("input tidak valid, masukan angka");
            }
        } while (!inputValid);

        System.out.print("Yakin ubah harga " + menu.nama + "?(ya/tidak): ");
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            menu.harga = hargaBaru;
            System.out.println("harga berhasil diperbarui");
        } else {
            System.out.println("perubahan dibatalkan");
        }
    }

    static void hapusMenu() {
        tampilkanDaftarMenu();

        //validasi
        int nomor = 0;
        boolean inputValid = false;
        do {
            System.out.print("masukan nomor yg ingin dihapus: ");
            String inputNomor = input.nextLine().trim();
            try {
                nomor = Integer.parseInt(inputNomor);
                if (nomor>=1&&nomor<= daftarMenu.size()) {
                    inputValid = true;
                } else {
                    System.out.println("nomor tidak valid, masukan nomor antara 1 sampai" + daftarMenu.size());
                }
            } catch (NumberFormatException e){
                System.out.println("input tidak valid, masukan angka");
            }
        } while (!inputValid);

        Menu menu = cariMenuBerdasarkanNomor(nomor);
        System.out.print("yakin hapus menu " + menu.nama + "? (ya/tidak): ");
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("ya")) {
            daftarMenu.remove(menu);
            System.out.println("menu berhasil dihapus");
        } else {
            System.out.println("penghapusan dibatalkan");
        }
    }

    //fungsi umum
    
    //perbaikan format
    static String formatRupiah(int nilai) {
        return String.format("%,d", nilai).replace(',','.');
    }

    static void tampilkanDaftarMenu() {
        int nomor = 1;

        System.out.println("\n-- Makanan --");
        for (Menu m : daftarMenu) {
            if (m.kategori.equalsIgnoreCase("Makanan")) {
                m.tampilkanMenu((nomor));
                nomor++;
            }
        }

        System.out.println("\n-- Minuman --");
        for (Menu m : daftarMenu) {
            if (m.kategori.equalsIgnoreCase("Minuman")) {
                m.tampilkanMenu(nomor);
                nomor++;
            }
        }
    }

    static Menu cariMenuBerdasarkanNomor(int nomor) {
        if (nomor<1||nomor> daftarMenu.size()) {
            return null;
        }
        int currentNomor = 1;
        for (Menu m : daftarMenu) {
            if(m.kategori.equalsIgnoreCase("makanan")) {
                if (currentNomor == nomor) return m;
                currentNomor++;
            }
        }
        for (Menu m : daftarMenu) {
            if (m.kategori.equalsIgnoreCase("minuman")) {
                if (currentNomor == nomor) return m;
                currentNomor++;
            }
        }
        return null;
    }

    static Menu cariMenu(String nama) {
        for (Menu m: daftarMenu) {
            if (m.nama.equalsIgnoreCase(nama))
            return m;
        }
        return null;
    }

    static void hitungDanCetakStruk(ArrayList<String> namaPesanan, ArrayList<Integer> jumlahPesanan) {
        int total = 0;
        boolean adaMinuman = false;

        for (int i=0; i<namaPesanan.size();i++) {
            Menu m = cariMenu(namaPesanan.get(i));
            if (m !=null) {
                total += m.harga * jumlahPesanan.get(i);
                if (m.kategori.equalsIgnoreCase("Minuman")) adaMinuman = true;
            }
        }

        //promo b1g1
        Menu promoItem = cariMenu("Es Teh");
        boolean dapatPromo = total> 50000 && adaMinuman;
        int potonganPromo = 0;
        if (dapatPromo&&promoItem !=null) {
            System.out.println("\n Anda berhak mendapatkan prompo beli 1 gratis 1");
            System.out.println("Minuman gratis: " + promoItem.nama + "(Rp "+formatRupiah(promoItem.harga)+")");
            System.out.print("apakah anda ingin mengambil promo ini? (ya/tidak): ");
            String jawab = input.nextLine().trim();
            if (jawab.equalsIgnoreCase("ya")) {
                total += promoItem.harga;
                potonganPromo = promoItem.harga;
                System.out.println("promo berhasil dimabil\n");
            } else {
                dapatPromo = false;
                System.out.println("promo tidak diambil\n");
            }
        }

        boolean dapatDiskon = total> 100000;
        int diskon = dapatDiskon ? (int)(total*0.10):0;
        double pajak = total*0.10;
        int biayaPelayanan = 20000;
        int totalAkhir = total-potonganPromo-diskon+(int)pajak+biayaPelayanan;
        
        //cetak struk
        System.out.println("\n==============================================");
        System.out.println("              Struk Pemesanan                 ");
        System.out.println("==============================================");
        System.out.printf("%-20s %-10s %-15s%n", "Menu","Jumlah","SubTotal");
        System.out.println("----------------------------------------------");

        for (int i=0;i<namaPesanan.size();i++){
        Menu m = cariMenu(namaPesanan.get(i));
        if (m != null)
            System.out.printf("%-20s %-10d %s%n",m.nama,jumlahPesanan.get(i),formatRupiah(m.harga*jumlahPesanan.get(i)));
        }

        if (dapatPromo&&promoItem !=null)
            System.out.printf("%-20s %-10s %s%n", promoItem.nama+"(Promo B1G1)", "1", formatRupiah(potonganPromo));
        
        System.out.println("----------------------------------------------");
        System.out.printf("%-25s: Rp %s%n", "Subtotal Pesanan", formatRupiah(total));
        System.out.printf("%-25s: Rp %s%n", "Pajak(10%)", formatRupiah((int)pajak));
        System.out.printf("%-25s: Rp %s%n", "Biaya Pelayanan", formatRupiah(biayaPelayanan));
        if (dapatPromo)
            System.out.printf("%-25s: Rp %s%n", "Diskon B1G1", formatRupiah(potonganPromo));
        if (dapatDiskon)
            System.out.printf("%-25s: Rp %s%n", "Diskon 10%", formatRupiah(diskon));
        
        System.out.println("----------------------------------------------");
        System.out.printf("%-25s: Rp %s%n", "TOTAL BAYAR", formatRupiah(totalAkhir));
        System.out.println("==============================================");
        System.out.println("Terimakasih telah memesan di Restoran kami");
        System.out.println("==============================================");
    } 
}