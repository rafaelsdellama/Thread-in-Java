
/**
 *
 * @author marianabm
 */
public class Exe2 {
    
    public static void main(String[] args) {
        
        Toalete toalete;
        ThreadGroup grupo;
        
		toalete = new Toalete();            
		grupo = new ThreadGroup("Toalete");
        
		for(int i = 0; i < args.length; i = i+2) {
			
			Thread tm = new Thread(grupo, new ClienteMulher(args[i], toalete));
			tm.start();

			Thread th = new Thread(grupo, new ClienteHomem(args[i+1], toalete));
			th.start();
		}            
            
		while(grupo.activeCount() > 0){
			try{
				Thread.sleep(50);
			}
			catch(InterruptedException ie){
				System.out.println("Erro" + ie);		
			}
		}
    }
    
}
