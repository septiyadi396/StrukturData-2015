import java.io.FileWriter;
import java.io.IOException;

public class CariPrima {
    public static void main() throws IOException {
        // Buat berkas untuk menulis hasil. Pakai WRITER karena yang ditulis 
        // berkas text.
        FileWriter berkas = new FileWriter(NAMA_BERKAS);
        
        // Buat array dari thread
        BenarPrima[] benarPrima = new BenarPrima[JUMLAH_THREAD];
        Thread[] thread = new Thread[JUMLAH_THREAD];
        // Mulai hitung dari angka 2, karena 1 otomatis bukan prima
        int angka = 2;
        // Loop sampai batas atas yang diminta
        while (angka<=ANGKA_TERBESAR) {
            ///////////////////////////////////////////////////////////////////
            // MASUKKAN KODE ANDA DI BAWAH INI
            
            for (int cnt=0; cnt<JUMLAH_THREAD; cnt++) {
                benarPrima[cnt] = new BenarPrima(angka);
                thread[cnt] = new Thread(benarPrima[cnt]);     
                angka++;
            }
            
            for (int cnt=0; cnt<JUMLAH_THREAD; ++cnt) {
                thread[cnt].start();       
            }
                        
            // Tunggu sampai semua thread selesai
            for (int counterThread=0; counterThread<JUMLAH_THREAD; ++counterThread)
                while (benarPrima[counterThread].selesai() == false) { }
            
            for(int cnt=0; cnt<JUMLAH_THREAD;cnt++){
                if(benarPrima[cnt].selesai()){
                    if(benarPrima[cnt].prima()){
                        synchronized(berkas) {
                            try {
                                berkas.write(benarPrima[cnt].angka()+"\n");
                            }
                            catch (IOException kesalahan) {
                                System.out.printf("Terjadi kesalahan: %s", kesalahan);
                            }
                        }
                    }
                }
            }            
            
            // MASUKKAN KODE ANDA DI ATAS INI
            ///////////////////////////////////////////////////////////////////            
        }                        
        
        // Tutup berkas untuk menulis hasil
        berkas.close();
    }
    
    private final static String NAMA_BERKAS = "prima.log";
    private final static int JUMLAH_THREAD = 10;
    private final static int ANGKA_TERBESAR = 100000;
}