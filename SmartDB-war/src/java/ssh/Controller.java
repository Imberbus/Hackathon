package ssh;

import java.io.IOException;
import java.net.UnknownHostException;

import com.jcraft.jsch.JSchException;

import log.Log;

public class Controller {

	private String TAG;
	private String IPrt;
	private String LOGIN;
	private String PASS ;
	private int SSHPORT ;
	private String nameScript;
	

	public Controller(String rt,String log, String pass, int port, String Tag) 
	{
		//Connect to routeur and exec script ****** Create A Container *******
		this.setIPrt(rt);
		this.setLOGIN(log);
		this.setPASS(pass);		
		this.setNameScript("mkdir test");	
		this.setSSHPORT(port);
		this.setTAG(Tag);
	}
	
	//Connect to routeur and exec script ****** Create A Container *******
	public static void testSSH(String Login, String MDP, String Script, String IPrt, int Port, String TAG) throws InterruptedException 
	{
		Log.d(TAG, "Connection ssh to : "+Login+"@"+IPrt);
		Log.d(TAG, "Commande is : "+Script);
		try {
			SSHRouterClient.sendCommand(IPrt, Port, Login, MDP, Script);
			Log.d(TAG, "Commande Well done....");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			Log.e(TAG, "Could not execute commande");
		} catch (JSchException e) {
			e.printStackTrace();
			Log.e(TAG, "Could not execute commande");
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "Could not execute commande");
		}
		Thread.sleep(5000);
	}
        
        //Connect to routeur and exec script ****** Create A Container *******
		public static String getSize(String Login, String MDP, String Script, String IPrt, int Port, String TAG) throws InterruptedException 
		{
			String result1 = "";
			Log.d(TAG, "Connection ssh to : "+Login+"@"+IPrt);
			Log.d(TAG, "Commande is : "+Script);
			try {
				result1 = SSHRouterClient.sendCommandResult(IPrt, Port, Login, MDP, Script);
				Log.d(TAG, "Commande Well done....");
			} catch (UnknownHostException e) {
				e.printStackTrace();
				Log.e(TAG, "Could not execute commande");
			} catch (JSchException e) {
				e.printStackTrace();
				Log.e(TAG, "Could not execute commande");
			} catch (IOException e) {
				e.printStackTrace();
				Log.e(TAG, "Could not execute commande");
			}
			Thread.sleep(5000);
			return result1;
		}
	

	public String getNameScript() {
		return nameScript;
	}

	public void setNameScript(String nameScript) {
		this.nameScript = nameScript;
	}

	public String getIPrt() {
		return IPrt;
	}

	public void setIPrt(String iPrt) {
		IPrt = iPrt;
	}

	public String getPASS() {
		return PASS;
	}

	public void setPASS(String pASS) {
		PASS = pASS;
	}

	public int getSSHPORT() {
		return SSHPORT;
	}

	public void setSSHPORT(int sSHPORT) {
		SSHPORT = sSHPORT;
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}

	public String getTAG() {
		return TAG;
	}

	public void setTAG(String tAG) {
		TAG = tAG;
	}

	/**
	 * Ici tout en bas, on va  définir les parametres de chaque script et creer les fonctions
	 * 
	 * 1-Ajouter un noeud au cluster (add_node_cluster.sh)
	 * #param1: IP-ADDRESS-CLUSTER-Node
	 * 
	 * 2-Creer un cluster (create_cluster.sh)
	 * #param1: clustername	
	 * 
	 * 3-Creer un container (create_container.sh)
	 * #param1: vmid
	 * #param2: vmdiskspace
	 * #param3: vmostemplate
	 * #param4: vmrootpasswd
	 * #param5: vmhostname
	 * #param6: vmdescription
	 * #param7: vmonboot
	 * #param8: vmswap
	 * #param9: vmmem
	 * #param10: vmcpu
	 * #param11: vmifdev
	 * #param12: vmbridge
	 * #param13: vmip
	 * 
	 * 4-Supprimer un noeud du cluster (del_node_cluster.sh)
	 * #param1: CLUSTER-Node
	 * 
	 * 5-Detruire un container (destroy_container.sh)
	 * #param1: vmid
	 * 
	 * 6-Entrer dans un container (enter_container.sh)
	 * #param1: vmid
	 * 
	 * 7-Sortir dun container (exit_container.sh)
	 * 
	 * 8-Migrer un container (migrer_container.sh)
	 * #param1: vmid	
	 * #param2: target
	 * 
	 * 9-modifier le parametre bridge du container (set_container_bridge.sh)
	 * #param1: vmid
	 * #param2: vmifbriadge
	 * 
	 * 10-modifier le parametre cpu du container (set_container_cpu.sh)
	 * #param1: vmid
	 * #param2: vmcpu
	 * 
	 * 11-modifier le parametre diskspace du container (set_container_diskspace.sh)
	 * #param1: vmid
	 * #param2: vmdiskspace
	 *  
	 * 12-modifier le parametre ifdev du container (set_container_ifdev.sh)
	 * #param1: vmid
	 * #param2: vmifdev
	 * 
	 * 13-modifier le parametre ip du container (set_container_ip.sh)
	 * #param1: vmid
	 * #param2: vmip
	 *  
	 * 14-modifier le parametre mem du container (set_container_mem.sh)
	 * #param1: vmid
	 * #param2: vmmem
	 * 
	 * 15-modifier le parametre swap du container (set_container_swap.sh)
	 * #param1: vmid
	 * #param2: vmswap
	 * 
	 * 16-Lancer un container (start_container.sh)
	 * #param1: vmid
	 * 
	 * 17-Stopper un container (stop_container.sh)
	 * #param1: vmid 
	 * 
	 */
	
	//1-Ajouter un noeud au cluster (add_node_cluster.sh)
	public String add_node_cluster(String IP_ADDRESS_CLUSTER_Node) 
	{
		String name="./add_node_cluster.sh";
		String params=" "+IP_ADDRESS_CLUSTER_Node;
		return name+params;
	}
	
	//2-Creer un cluster (create_cluster.sh)
	public String create_cluster(String clustername)
	{
		String name="./create_cluster.sh";
		String params=" "+clustername;	
		return name+params;
	}	
	
	//3-Creer un container (create_container.sh)
	public String create_container(String vmid,String vmdiskspace,String vmrootpasswd, String vmhostname, String vmonboot, String vmmem, String vmifdev, String vmbridge, String vmip) 
	{
		String name="./create_container.sh";
		String params=" "+vmid+" "+vmip+" "+vmonboot+" "+vmhostname+" "+vmmem+" "+vmdiskspace+" "+vmifdev+" "+vmbridge+" "+vmrootpasswd;
		return name+params;
	}
	
	//4-Supprimer un noeud du cluster (del_node_cluster.sh)
	public String del_node_cluster(String CLUSTER_Node)
	{
		String name="./del_node_cluster.sh";
		String params=" "+CLUSTER_Node;	
		return name+params;
	}
	
	//5-Detruire un container (destroy_container.sh)
	public String destroy_container(String vmid)
	{
		String name="./destroy_container.sh";
		String params=" "+vmid;	
		return name+params;
	}
	
	//6-Entrer dans un container (enter_container.sh)
	public String enter_container(String vmid)
	{
		String name="./enter_container.sh";
		String params=" "+vmid;	
		return name+params;
	}
	
	//7-Sortir dun container (exit_container.sh)
	public String exit_container()
	{
		String name="./exit_container.sh";;	
		return name;
	}
	
	//8-Migrer un container (migrer_container.sh)
	public String migrer_container(String vmid,String target)
	{
		String name="./migrer_container.sh";
		String params=" "+vmid+" "+target;	
		return name+params;
	}
	
	//9-modifier le parametre bridge du container (set_container_bridge.sh)
	public String set_container_bridge(String vmid,String vmbridge)
	{
		String name="./set_container_bridge.sh";
		String params=" "+vmid+" "+vmbridge;	
		return name+params;
	}
	
	//10-modifier le parametre cpu du container (set_container_cpu.sh)
	public String set_container_cpu(String vmid,String vmcpu)
	{
		String name="./set_container_cpu.sh";
		String params=" "+vmid+" "+vmcpu;	
		return name+params;
	}
	
	//11-modifier le parametre diskspace du container (set_container_diskspace.sh)
	public String set_container_diskspace(String vmid,String vmdiskspace)
	{
		String name="./set_container_diskspace.sh";
		String params=" "+vmid+" "+vmdiskspace;	
		return name+params;
	}
	
	//12-modifier le parametre ifdev du container (set_container_ifdev.sh)
	public String set_container_ifdev(String vmid,String vmifdev)
	{
		String name="./set_container_ifdev.sh";
		String params=" "+vmid+" "+vmifdev;	
		return name+params;
	}
	
	//13-modifier le parametre ip du container (set_container_ip.sh)
	public String set_container_ip(String vmid,String vmip)
	{
		String name="./set_container_ip.sh";
		String params=" "+vmid+" "+vmip;	
		return name+params;
	}
	
	//14-modifier le parametre mem du container (set_container_mem.sh)
	public String set_container_mem(String vmid,String vmmem)
	{
		String name="./set_container_mem.sh";
		String params=" "+vmid+" "+vmmem;	
		return name+params;
	}
	
	//15-modifier le parametre swap du container (set_container_swap.sh)
	public String set_container_swap(String vmid,String vmswap)
	{
		String name="./set_container_swap.sh";
		String params=" "+vmid+" "+vmswap;	
		return name+params;
	}
	
	//16-Lancer un container (start_container.sh)
	public String start_container(String vmid)
	{
		String name="./start_container.sh";
		String params=" "+vmid;	
		return name+params;
	}

	//17-Stopper un container (stop_container.sh)
	public String stop_container(String vmid)
	{
		String name="./stop_container.sh";
		String params=" "+vmid;	
		return name+params;
	}
}
