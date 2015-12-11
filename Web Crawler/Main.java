import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try {
            webCrawler tanya = new webCrawler();
            tanya.whois("GET index.html.newline");
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
