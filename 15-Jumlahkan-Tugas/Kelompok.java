
/**
 * Write a description of class Jumlahkan here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kelompok implements Runnable{
    private int awal;
    private int akhir;
    private double[] data = new double[5];
    private double nilaiHasil;
    
    public Kelompok(int awal, int akhir, double[] data){
        this.data=data;
        this.awal=awal;
        this.akhir=akhir;
    }
    
    public double hasil(){
        return nilaiHasil;
    }
    
    public void hitung(){
        for( int counter = awal ; counter<=akhir;counter++) 
            nilaiHasil+=data[counter];         
    }
    
    public void run(){
        hitung();
    }
}
