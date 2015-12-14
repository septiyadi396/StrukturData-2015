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
            int i;
            System.out.println("Tebak Angka");

            for(i=0; i<3; i++){
                // masukkan angka
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Angka : ");
                baris=keyboard.nextLine();

                // tulis ke socket
                Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
                BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
                keluaranBuff.write(baris);
                keluaranBuff.write("\n");
                keluaranBuff.flush();

                // baca dari Server
                System.out.print("Dari server: ");
                masukan = new InputStreamReader(socket.getInputStream()); 
                masukanBuff = new BufferedReader(masukan);
                baris = masukanBuff.readLine();
                System.out.println(baris);         

                if(baris.equalsIgnoreCase("Benar"))
                    break;
            }

            if(i==3)
                masukan = new InputStreamReader(socket.getInputStream()); 
            masukanBuff = new BufferedReader(masukan);
            baris = masukanBuff.readLine();
            System.out.println(""+baris);
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