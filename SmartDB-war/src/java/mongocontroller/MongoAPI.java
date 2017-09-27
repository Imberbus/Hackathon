/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongocontroller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Samih
 */
public class MongoAPI {
    
    /* HOW TO USE
        L'utilisation de ces fonctions est semblable à l'utilisation que vous feriez sur MongoDB. 
        La fonction initClient permet d'ouvrir une session. Si vous ne faites pas ça au début, vous ne pouvez
        rien faire, comme sur le serveur mongoDB. Vous devez donc créer une session avec le compte root
        préconfiguré sur les conteneurs, dont vous trouverez les ID sur le drive.
    
        Ensuite, vous pouvez créer un utilisateur et lui donner des roles. Ceux ci sont définis dans un tableau
        qu'il ne faut surtout pas initialiser avec un nombre fixe de case sans toutes les remplir. Initialisez le
        avec exactement le bon nombre de cases pour chaque role envisagé. Exemple : 
    
        String[] roles = {"userAdmin","readWrite"};
    
        Si vous souhaitez créer un utilisateur sur une BDD donnée, créez d'abord la BDD et l'utilisateur ensuite.
        
        Il est normal qu'une BDD vide aie une taille de 0 : dans mongoDB, les BDD ne sont vraiment comptabilisées
        que lorsqu'on y ajoute quelque chose.
    
        Si vous êtes gêné par la taille de 0 des BDD vides et le fait qu'elles n'apparaissent pas dans la liste, 
        vous pouvez utiliser la fonction addData qui rajoutera des informations de remplissage afin que la BDD
        soit prise en compte.
    
    ATTENTION : Vous aurez une erreur si vous essayez de : 
    - faire quoi que ce soit sans être logué avec les droits nécessaires
    - créer un utilisateur qui existe déjà
    - vous loguer avec un compte qui n'existe pas
    - créer un utilisateur qui a plus de droits que vous
    - accéder à une BDD à laquelle vous n'avez pas accès(ou même afficher la liste des BDD sans être admin)
    */

    MongoClient client;

    //Fonction d'identification
    public void initClient(String uname, String pwd, int port, String bdd) {
        MongoClientURI uri = new MongoClientURI("mongodb://"+uname+":"+pwd+"@149.202.70.58:"+port+"/?authSource="+bdd);
        client = new MongoClient(uri);
    }
    
    public ArrayList<String> getallDbs() {
        ArrayList<String> res=new ArrayList<String>();
        for (String s : client.listDatabaseNames()) {
            res.add(s);
        }
        return res;
    }

    //Récupération de BDD
    public MongoDatabase getDatab(String name) {
        return client.getDatabase(name);
    }

    //Ajout d'informations de remplissage (pour tester)
    public void addData(String dbname) {
        MongoDatabase db = getDatab(dbname);
        MongoCollection col = db.getCollection("testttt");
        Document doc = new Document()
                        .append("street", "2 Avenue")
                        .append("zipcode", "10075")
                        .append("building", "1480");
        col.insertOne(doc);
    }
    
    //ajout utilisateur, attention à la syntaxe du tableau, se référer au "HOW TO USE" en haut de page
    public void addUser(String datab, String username, String pwd, String[] roles) {
        MongoDatabase db = getDatab(datab);
        Map<String, Object> commandArguments = new BasicDBObject();
        commandArguments.put("createUser", username);
        commandArguments.put("pwd", pwd);
        commandArguments.put("roles", roles);
        BasicDBObject command = new BasicDBObject(commandArguments);
        db.runCommand(command);
    }
    
    //suppression utilisateur
    public void dropUser(String datab, String username) {
        MongoDatabase db = getDatab(datab);
        Map<String, Object> commandArguments = new BasicDBObject();
        commandArguments.put("dropUser", username);
        BasicDBObject command = new BasicDBObject(commandArguments);
        db.runCommand(command);
    }

    //suppression DB
    public void dropDB(String datab) {
        MongoDatabase db = getDatab(datab);
        db.drop();
    }
    
    //création dB
    public MongoDatabase createDB(String name) {
        return client.getDatabase(name);
    }
    
    //Retourne la taille des informations sur une DB
    public int getDBSize(String dbname) {
        DB db = client.getDB(dbname);
        return db.getStats().getInt("dataSize");
    }
    
    //Retourne le stockage total alloué à une DB
    public int getStorageSize(String dbname) {
        DB db = client.getDB(dbname);
        return db.getStats().getInt("storageSize");
    }
    
    public static void main(String[] args) {
        MongoAPI m = new MongoAPI();
        m.initClient("root", "1*smart-db*2", 4111, "admin");
        //m.dropDB("test");
        //m.dropUser("lolo","root1");
        //String[] roles = {"userAdmin","readWrite"};
        //m.createDB("lolo"); //<=== add in mongo database 
        //System.out.println("BEFORE ===> "+m.getDBSize("lolo"));
        //m.addUser("lolo", "root1", "root", roles);
        //m.initClient("root1", "root", 4111, "lolo");
        //m.addUser("test", "root", "root", roles);
        m.addData("tes");
        //System.out.println("AFTER ===> "+m.getDBSize("test"));
        
        /***Lorsque je lance le programme, jai cette erreur :
         *
         * run:
            Exception in thread "main" java.lang.NullPointerException
                at mongocontroller.MongoAPI.getDatab(MongoAPI.java:34)
                at mongocontroller.MongoAPI.addUser(MongoAPI.java:50)
                at mongocontroller.MongoAPI.main(MongoAPI.java:83)
            Java Result: 1
            BUILD SUCCESSFUL (total time: 0 seconds)         
         **/

       // m.initClient("root", "1*smart-db*2", 4110, "admin");
       // System.out.println(m.getDBSize("admin")+" "+m.getStorageSize("admin"));
    }
}
