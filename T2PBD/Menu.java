public class Menu {

    String nama;
    int harga;
    String kategori;

    public Menu(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    public void tampilkanMenu(int nomor) {
        System.out.printf("%d. %-20s Rp %s%n", nomor, nama, String.format("%,d", harga).replace(',','.'));
    }

    @Override
    public String toString() {
        return String.format("%-20s Rp %s (%s)", nama, String.format("%,d", harga).replace(',','.'), kategori);
    
    }
}