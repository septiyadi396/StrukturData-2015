import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Client {
    public void chat() 
    throws UnknownHostException, IOException {
        Socket socket = new Socket("192.168.43.48", 33333);
        try {
            String baris=null;
            Reader masukan=null;
            BufferedReader masukanBuff=null;

            // masukkan perintah
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Perintah : ");
            baris=keyboard.nextLine();

            // tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write(baris);
            keluaranBuff.write("\n");
            keluaranBuff.flush();

            // baca dari Server
            if(baris.equalsIgnoreCase("SIAPA")){
                System.out.print("Balasan server : ");
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                System.out.println(baris);         
            }
            else if(baris.equalsIgnoreCase("WAKTU")){
                System.out.print("Balasan server : ");
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                System.out.println(baris);
            }
            else{
                System.out.print("Balasan server : ");
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                System.out.println(baris);
            }
            socket.close();
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (socket != null)
                socket.close();
        }
    }
}