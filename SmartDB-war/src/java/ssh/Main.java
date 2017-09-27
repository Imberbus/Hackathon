package ssh;

public class Main {

	public static void main(String args[]) throws InterruptedException
	{
		/**
		 * Ici, on va tester un lancement de script de creation et de lancement de container
		 * 
		 */
		
		//Controller ctrlr = new Controller("149.202.70.58","smartdb", "smartdb1212**",22,"Controller");	
		Controller ctrlr = new Controller("149.202.70.58","root", "1*EYnrLcSkqAj1*2",22,"Controller");		
		String script;
		
		/************************* LANCER CONTAINER *******************************/
		// creation container 101
                script=ctrlr.set_container_diskspace("111", "5.34");
		//script=ctrlr.create_container("110", "10","admin","vm110","1","4096","eth0", "vmbr0", "192.168.0.110");
	    Controller.testSSH(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
	   
	   // lancer container 101
	   //script = ctrlr.start_container("110");
	   //Controller.testSSH(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
       
	   /************************* ARRET CONTAINER *******************************/		
	   // stopper container 101
	   /*script = ctrlr.stop_container("101");
	   Controller.testSSH(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
        	   
	   // detruire container 101
	   script = ctrlr.destroy_container("101");
	   Controller.testSSH(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
	   
	   /************************* MISE A JOUR *******************************/	
	   //modifier le parametre diskspace du container 101
	   //script = ctrlr.set_container_diskspace("101","90");
	   //Controller.testSSH(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
	}

}
