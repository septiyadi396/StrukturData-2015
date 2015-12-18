import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ProcessServerThread implements Runnable {
    private Socket koneksi;
    private int angka=0;

    public ProcessServerThread(Socket koneksiKiriman, int angka) {
        koneksi = koneksiKiriman;
        this.angka=angka;
    }

    public void run(){
        try{
            if (koneksi != null)
                prosesPermintaanClient();
        }catch(IOException err) {
            System.out.println(err);
        }
    }

    private void prosesPermintaanClient() throws IOException {
        FileWriter berkas = new FileWriter(NAMA_BERKAS, false);
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari : \n" + ip);
        System.out.println("Jumlah : "+angka);
        String pesanServer=null;
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;
        
        while(true){
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan));             
            String baris = masukanReader.readLine();                               

            if (baris.equals("TAMBAH")){
                angka++;
            }                                    
            else if (baris.equals("KURANG")){
                angka--;
            }
            else if (baris.equals("JUMLAH")){
                keluaran = koneksi.getOutputStream();
                keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                keluaranBuf.write("Jumlah : "+angka);
                keluaranBuf.newLine();
                keluaranBuf.flush();
            }
            else if (baris.equals("SELESAI")){
                berkas.close();
                break;
            }
            else {
                keluaran = koneksi.getOutputStream();
                keluaranBuf = new BufferedWriter (new OutputStreamWriter(keluaran));
                keluaranBuf.write("Input salah");
                keluaranBuf.newLine();
                keluaranBuf.flush();
            }
            System.out.println("Jumlah : "+angka); 
        }        
    }
    private final static String NAMA_BERKAS = "stok.txt";
}