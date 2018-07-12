
import java.util.Random;

/**
 *
 * @author marianabm
 */
public class ClienteHomem implements Runnable{
    
    String name;
    Toalete toalete;
    
    public ClienteHomem(String name, Toalete toalete){
        this.name = name;
        this.toalete = toalete;
    }
    
    @Override
    public void run() {
        Random gerador = new Random();
        try {
            Thread.sleep(gerador.nextInt(6) * 1000);            
            toalete.homemQuerEntrar(name);
            
            Thread.sleep(gerador.nextInt(6) * 1000);
            toalete.homemSaiToalete(name);            
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } 
    }
    
}
