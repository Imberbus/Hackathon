/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elasticity;

import ssh.Controller;

/**
 *
 * @author yann
 */
public class Elasticity {
    
        private Controller ctrlr ;		
	private String script ; 
	private	String result;		
		
    public Elasticity() 
    {}
    
    //Retourne l'IP d'une vm
    public static int getSizeofContainer(int portSSH) throws InterruptedException { 
        Controller ctrlr  = new Controller("149.202.70.58","root", "proxmox",portSSH,"Controller");	
        String script ="df -h";
        String result = Controller.getSize(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
   
        String[] tabResult = result.split("[ ]+");
	String sizeBrut = tabResult[3];
	char unite = sizeBrut.substring(sizeBrut.length()-1).charAt(0);
	String zerosDeConversion = "";
	if (unite == 'M') {
		zerosDeConversion = "000";
	} else if (unite == 'G') {
		zerosDeConversion = "000000";
	}
	//System.out.println("Taaaaaillle dispo : " + sizeBrut);
	String pureSize = sizeBrut.substring(0, sizeBrut.length()-1);
	String convertedSize = pureSize.concat(zerosDeConversion); 
	//System.out.println("Taaaaaillle dispo : " + convertedSize);
        return Integer.parseInt(convertedSize);
    }
    
    public static void main(String[] args) throws InterruptedException {
        
                Elasticity x= new Elasticity(); 
                getSizeofContainer(2111);
                System.out.println("Taille = "+getSizeofContainer(2111)+" ko");
        
    }
}
