
import java.util.Random;

/**
 *
 * @author marianabm
 */
public class ClienteMulher implements Runnable{
    
    String name;
    Toalete toalete;
    
    public ClienteMulher(String name, Toalete toalete){
        this.name = name;
        this.toalete = toalete;
    }

    @Override
    public void run() {
        Random gerador = new Random();
        try {
            Thread.sleep(gerador.nextInt(6) * 1000);            			
            toalete.mulherQuerEntrar(name);
            
            Thread.sleep(gerador.nextInt(6) * 1000);
            toalete.mulherSaiToalete(name);            
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }        
    }
    
}
