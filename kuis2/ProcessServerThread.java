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
import java.util.Calendar;
public class ProcessServerThread implements Runnable {
    private Socket koneksi;
    public ProcessServerThread(Socket koneksiKiriman, int angka) {
        koneksi = koneksiKiriman;
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
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println(ip+" terhubung.");        
        OutputStream keluaran=null;
        BufferedWriter keluaranBuf=null;
        
        // Ambil dan tampilkan masukan
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan));             
        String baris = masukanReader.readLine();       
        String[] bariss = baris.split(" ");
        
        if (baris.equalsIgnoreCase("SIAPA")){
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write(ip);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }                                    
        else if (baris.equalsIgnoreCase("WAKTU")){
            Calendar kalender = Calendar.getInstance();
            String waktuStr = kalender.getTime().toString();
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write(waktuStr);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }            
        else if (bariss[0].equalsIgnoreCase("WAKTU")){
            int a = Integer.parseInt(bariss[1]);
            Calendar kalender = Calendar.getInstance();
            kalender.add(Calendar.HOUR_OF_DAY, a-7);
            String waktu = kalender.getTime().toString();
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
            keluaranBuf.write(waktu);
            keluaranBuf.newLine();
            keluaranBuf.flush();
        }
        else {
            keluaran = koneksi.getOutputStream();
            keluaranBuf = new BufferedWriter (new OutputStreamWriter(keluaran));
            keluaranBuf.write("Perintah tidak dikenal!");
            keluaranBuf.newLine();
            keluaranBuf.flush();            
        }
        System.out.println(ip+" me-request "+baris);
    }
}