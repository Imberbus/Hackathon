/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import Elasticity.Elasticity;
import static Elasticity.Elasticity.getSizeofContainer;
import static database.DatabaseManager.listOfContainerID;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import mongocontroller.MongoAPI;
import ssh.Controller;

/**
 *
 * @author Samih
 */
public class Script {

    public static void main(String[] args) throws InterruptedException, SQLException {
        Connection c1 = database.DatabaseManager.connectionDatabase();
        ArrayList<Integer> conts = listOfContainerID(c1);
        c1.close();
        while (true) {
            for (int cont : conts) {//pour chaque conteneur
                //Taille occuppée par lees VMs (en octets)
                int tailleCont = 0;
                MongoAPI m = new MongoAPI();
                m.initClient("root", "1*smart-db*2", 4000 + cont, "admin");
                for (String bdd : m.getallDbs()) {
                    tailleCont+=m.getStorageSize(bdd);
                    System.out.println(bdd);
                }
                //Taille maximale du conteneur (ko)
                int tailleCont2=getSizeofContainer(2000+cont);
                //comparaison taille max et taille occuppée+
                if (tailleCont2*1000<tailleCont+100000000) {
                    //mettre le mdp root (et l'enlever avant commit hein yann)
                    Controller ctrlr = new Controller("149.202.70.58","root", "",22,"Controller");
                    //conversion tailleCont2 en giga et ajout de 200 méga
                    String script = ctrlr.set_container_diskspace(cont+"", (((float)tailleCont2/1000000)+0.2)+"");
                    Controller.testSSH(ctrlr.getLOGIN(),ctrlr.getPASS(), script,ctrlr.getIPrt(),ctrlr.getSSHPORT(),ctrlr.getTAG());
                }
                
            }
            Thread.sleep(3600000);
        }
    }

}
