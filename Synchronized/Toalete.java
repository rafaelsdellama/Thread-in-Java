
public class Toalete {    
    
    public String status;
    public static int clientesNoToalete;
    
    public Toalete() {
        status = "vazio";
        clientesNoToalete = 0;
    }
    
    synchronized public void mulherQuerEntrar(String name) {
        if(status.equals("com homem")) {
            try {
                System.out.println(name + " esperando...");
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        status = "com mulher";        
        clientesNoToalete++;       
        System.out.println(name + " está no toalete");        
   }
   
    synchronized public void homemQuerEntrar(String name) {
        if(status.equals("com mulher")) {
            try {
                System.out.println(name + " esperando...");
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        status = "com homem";        
        clientesNoToalete++;        
        System.out.println(name + " está no toalete");        
    }
   
   synchronized public void mulherSaiToalete(String name) {
       clientesNoToalete--;
       System.out.println(name + " sai do toalete");
       if(clientesNoToalete == 0) {
            status = "vazio";
            notifyAll();
       }           
   }
   
   synchronized public void homemSaiToalete(String name) {
       clientesNoToalete--;
       System.out.println(name + " sai do toalete");
       if(clientesNoToalete == 0) {
            status = "vazio";
            notifyAll();
       }       
   }
    
}
