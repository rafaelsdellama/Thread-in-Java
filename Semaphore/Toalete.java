
import java.util.concurrent.Semaphore;

/**
 *
 * @author marianabm
 */
public class Toalete {    
    
    private String status;
    private static int clientesNoToalete;
    private Semaphore mutex;
    private Semaphore queue; //fila de espera
    
    public Toalete() {
        status = "vazio";
        clientesNoToalete = 0;
        mutex = new Semaphore(1, true);        
        queue = new Semaphore(0, true); 
    }
    
    public void mulherQuerEntrar(String name) {
        try {
            mutex.acquire();
			while(true){
				if(status.equals("com homem")) {            
					System.out.println(name + " esperando...");
					mutex.release();
					queue.acquire(); //adiciona uma mulher na fila de espera
					mutex.acquire();
				}
				if(status.equals("vazio")) {                              
					status = "com mulher";        
					clientesNoToalete++;       
					System.out.println(name + " está no toalete");
					break;
				} else if(status.equals("com mulher")){
					clientesNoToalete++;       
					System.out.println(name + " está no toalete");
					break;
				}
				
			}			
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }finally {			
            mutex.release();
        }
   }
   
    public void homemQuerEntrar(String name) {
        try {
            mutex.acquire();
			while(true){
				if(status.equals("com mulher")) {            
					System.out.println(name + " esperando...");
					mutex.release();
					queue.acquire(); //adiciona um homem na fila de espera
					mutex.acquire();
				}            
				if(status.equals("vazio")) {                               
					status = "com homem";        
					clientesNoToalete++;       
					System.out.println(name + " está no toalete");
					break;
				} else if(status.equals("com homem")){            
					clientesNoToalete++;       
					System.out.println(name + " está no toalete");
					break;
				}
				
			}
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally {			
            mutex.release();
        }
    } 
   
    public void mulherSaiToalete(String name) {
        try{
			int n;
            mutex.acquire();
            clientesNoToalete--;
            System.out.println(name + " sai do toalete");
            
            /* se todas as mulheres sairem do banheiro, os homens podem entrar */
            if(clientesNoToalete == 0) {
                status = "vazio";	
				n = queue.getQueueLength();
                for(int i = 0; i < n; i++)
                    queue.release();     
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally {			
            mutex.release();
        }
   }
   
    public void homemSaiToalete(String name) {
        try{
			int n;
            mutex.acquire();
            clientesNoToalete--;
            System.out.println(name + " sai do toalete");
            
            /* se todas as mulheres sairem do banheiro, os homens podem entrar */
            if(clientesNoToalete == 0) {
                status = "vazio";
                n = queue.getQueueLength();
                for(int i = 0; i < n; i++)
                    queue.release();         
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally {			
            mutex.release();
        }       
    }
    
}
