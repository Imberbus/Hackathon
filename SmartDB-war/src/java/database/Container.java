/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author yann
 */
public class Container {
    
    private static int numVmid;
    private static int numIP;
    
    public Container()
    {
        Container.numVmid=112;
        Container.numIP=35;
    }
          
    
    //Modifie l'id d'une vm
    public void setVmid() {        
        Container.numVmid=Container.numVmid+1;
    }
    
    //Modifie l'IP d'une vm
    public void setnumIP() {        
        Container.numIP= Container.numIP+1;
    }
    
    //Retourne l'id d'une vm
    public int getVmid() {        
        return Container.numVmid;
    }
    
    //Retourne l'IP d'une vm
    public int getnumIP() {        
        return Container.numIP;
    }
     public static void main(String[] args) {
         Container x= new Container(); 
         x.setVmid();
         System.out.println("id1 ="+x.getVmid());
         x.setnumIP();
         System.out.println("ip1 ="+x.getnumIP());
         x.setVmid();
         System.out.println("id2 ="+x.getVmid());
         x.setnumIP();
         System.out.println("ip2 ="+x.getnumIP());
     }
}
