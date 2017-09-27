/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mongocontroller.MongoAPI;

/**
 * Cette classe a pour but d'intéragir avec la base de données
 *
 * @author salah
 */
public class DatabaseManager {

    private static String username = "root";
    private static String password = "root";
    private static String serverName = "localhost";
    private static String portNumber = "3306"; // 8889 or 3306
    private static String dbName = "smartdb-members";

    /**
     * Retourne le lien de connection après l'avoir établi
     */
    public static Connection connectionDatabase() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName, username, password);
        System.out.println("Connected to database");
        return conn;
    }

    /**
     * Retourne true si la paire email,mdp existe. False sinon
     */
    public static Boolean verifConnectionClient(Connection con, String email, String mdp) {
        Boolean exist = false;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT id FROM members WHERE email = '" + email + "' and pwd = '" + mdp + "'");
            if (resultset.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    /**
     * Retourne true si l'email existe. False sinon
     */
    public static Boolean existClient(Connection con, String email) {
        Boolean exist = false;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM members WHERE email = '" + email + "'");
            if (resultset.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public static Boolean existCapsule(Connection con, String nom) {
        Boolean exist = false;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM capsules WHERE nom = '" + nom + "'");
            if (resultset.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }
    /**
     * Crée un nouveau client, au cas ou ce client n'a pas encore de BDD,
     * remplir les champs bdd et ip par "_", port par 0
     */
    public static Boolean createClient(Connection con, String email, String pwd, int vip, String bdd, String ip, int port) {
        Boolean notexist = false;
        try {
            if (!existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("INSERT INTO `members`(`email`, `pwd`, `vip`, `ip`, `port`, `bdd`) VALUES ('" + email + "','" + pwd + "','" + vip + "','" + ip + "','" + port + "','" + bdd + "')");
                notexist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }
    
     public static Boolean createCapsule(Connection con, String mail, String nom, String taille, String date) {
        Boolean notexist = false;
        try {
            if (!existCapsule(con, nom)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("INSERT INTO `capsules`(`email`,`nom`, `taille`, `date`) VALUES ('" + mail + "','" + nom + "','" + taille + "','" + date + "')");
                notexist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * Supprime toutes les lignes d'un client de la table members
     */
    public static Boolean deleteClient(Connection con, String email) {
        Boolean notexist = false;
        try {
            if (existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("DELETE FROM members WHERE email = '" + email + "'");
                notexist = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    public static Boolean deleteCapsule(Connection con,String mail, String nom) {
        Boolean notexist = false;
        try {
            if (existCapsule(con, nom)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("DELETE FROM capsules WHERE nom = '" + nom + "' AND email ='" + mail + " '");
                notexist = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * Modifie le mot de passe de l'utilisateur ayant l'email "email" avec le
     * nouveau mot de passe "mdp" et renvoie "true" si l'email existe dans la
     * base de données
     */
    public static Boolean modifPwd(Connection con, String email, String mdp) {
        Boolean exist = false;
        try {
            if (existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("UPDATE members SET pwd='" + mdp + "' WHERE email='" + email + "'");
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    /**
     * Modifie le mot de passe de l'utilisateur ayant l'email "email" avec le
     * nouveau mot de passe "mdp" et renvoie "true" si l'email existe dans la
     * base de données
     */
    public static Boolean modifMail(Connection con, String email, String newmail) {
        Boolean exist = false;
        try {
            if (existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("UPDATE members SET email='" + newmail + "' WHERE email='" + email + "'");
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    /**
     * Retourne 1 si le client est vip, retourne 0 si non
     */
    public static int isVipClient(Connection con, String email) {

        int vipInt = 0;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT vip FROM members WHERE email = '" + email + "'");
            if (resultset.next()) {
                vipInt = resultset.getInt("vip");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vipInt;
    }

    /**
     * retourne le pwd d'un client
     */
    public static String hisPassword(Connection con, String email) {
        String pwd = "";
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT pwd FROM members WHERE email = '" + email + "'");
            if (resultset.next()) {
                pwd = resultset.getString("pwd");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pwd;
    }

    /**
     * retourne le pwd d'un client
     */
    public static ArrayList<Integer> listOfContainerID(Connection con) {
        ArrayList<Integer> listOfId = new ArrayList<Integer>();
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT vmid FROM container");
            //listOfId = (ArrayList<Integer>) resultset.getArray("vmid");
            int i;
            for (i = 0; resultset.next(); i++) {
                System.out.println("***********************");
                listOfId.add(resultset.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOfId;
    }

    /**
     * retourne le port d'un client
     */
    public static int hisPortDB(Connection con, String email) {
        int p = 0;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT port FROM members WHERE email = '" + email + "'");
            if (resultset.next()) {
                p = Integer.parseInt(resultset.getString("port"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    /**
     * retourne l'id d'un client
     */
    public static String hisID(Connection con, String email, String bdd) {
        String p = "";
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT uname FROM mybdd WHERE email = '" + email + "' and bdd= '" + bdd + "'");
            if (resultset.next()) {
                p = resultset.getString("uname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    /**
     * retourne le mdp d'un client
     */
    public static String hisPwd(Connection con, String email, String bdd) {
        String p = "";
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT pwd FROM mybdd WHERE email = '" + email + "' and bdd= '" + bdd + "'");
            if (resultset.next()) {
                p = resultset.getString("pwd");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    /**
     * retourne le port d'un client
     */
    public static int hisPort(Connection con, String email, String bdd) {
        int p = 0;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT port FROM mybdd WHERE email = '" + email + "' and bdd= '" + bdd + "'");
            if (resultset.next()) {
                p = Integer.parseInt(resultset.getString("port"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    /**
     * Ajoute une BDD (à laquelle est assoicié une ip et un numéro de port) à un
     * client
     */
    public static Boolean addDbClient(Connection con, String email, String bdd, String ip, int port, String user, String pass) {
        Boolean notexist = false;
        int isVip = DatabaseManager.isVipClient(con, email);
        String pwd = DatabaseManager.hisPassword(con, email);
        try {
            if (existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("INSERT INTO `members`(`email`, `pwd`, `vip`, `ip`, `port`, `bdd`) VALUES ('" + email + "','" + pwd + "','" + isVip + "','" + ip + "','" + port + "','" + bdd + "')");
                smt.executeUpdate("INSERT INTO `mybdd`(`email`, `bdd`, `uname`, `pwd`) VALUES ('" + email + "','" + bdd + "','" + user + "','" + pass + "')");
                System.out.println("**** email=" + email + " pwd=" + pwd + " vip=" + isVip + " ip=" + ip + " port=" + port + " bdd=" + bdd);
                notexist = true;
            } else {
                System.out.println("*********************************** PB in addDBClient");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * Supprime la ligne de la bdd donnée d'un client, s'il s'agit de sa
     * dernière BDD : on supprime la ligne associée et on rajoute une nouvelle
     * avec : bdd = "_", ip = "_" , et port = 0
     */
    public static Boolean deleteDbClient(Connection con, String email, String bdd) {
        Boolean notexist = false;
        try {
            if (existClient(con, email)) {
                Statement smt = con.createStatement();
                String currentPwd = DatabaseManager.hisPassword(con, email);
                smt.executeUpdate("DELETE FROM members WHERE email = '" + email + "' and bdd = '" + bdd + "'");
                smt.executeUpdate("DELETE FROM mybdd WHERE email = '" + email + "' and bdd = '" + bdd + "'");
                notexist = true;
                if (!existClient(con, email)) {
                    DatabaseManager.createClient(con, email, currentPwd, 0, "_", "_", 0);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * En cours
     */
    public static Boolean getHisDbList(Connection con, String email, String pwd, int vip, String bdd, String ip, int port) {
        Boolean notexist = false;
        try {
            if (!existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("INSERT INTO members (email,pwd,vip,bdd,ip,port) VALUE ('" + email + "','" + pwd + "','" + vip + "','" + bdd + "','" + ip + "','" + port + "')");
                notexist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * En cours
     */
    public static Boolean getIpAndPortForDb(Connection con, String email, String pwd, int vip, String bdd, String ip, int port) {
        Boolean notexist = false;
        try {
            if (!existClient(con, email)) {
                Statement smt = con.createStatement();
                smt.executeUpdate("INSERT INTO members (email,pwd,vip,bdd,ip,port) VALUE ('" + email + "','" + pwd + "','" + vip + "','" + bdd + "','" + ip + "','" + port + "')");
                notexist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * Vérifie l'existence du mail dans la table des utilisateurs, renvoie
     * "true" s'il existe, "false" sinon
     */
    public static Boolean existID(Connection con, String user, String mdp) {
        Boolean exist = false;
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM members WHERE email='" + user + "' AND pwd='" + mdp + "'");
            if (resultset.next()) {
                exist = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public static String getBDD(Connection con, String mail) {
        String recherche = "";
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM members WHERE email='" + mail + "' ");
            int nbr = 0;
            recherche = "<div class=\"form-group\" align=\"center\">\n"
                    + "                            <div><SELECT name=\"Bdd\" id=\"required\" align=\"center\">";
            recherche += "<option value=\"123456test**123456\" >Your databases</option>\n";
            while (resultset.next()) {
                String nom = resultset.getString(resultset.findColumn("bdd"));
                String s = "_";
                String s2 = "-";
                String s1 = "" + nom;
                if (s.equals(s1)) {
                    //nothing rest = ".";
                } else if (s2.equals(s1)) {
                    //nothing  rest = ".";
                } else {
                    recherche += "<option VALUE=\"" + nom + "\">" + nom + "</option>\n";
                }
                nbr++;

            }
            if (nbr > 0) {
                recherche += "</SELECT>\n"
                        + "                            </div>\n"
                        + "                        </div>";
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    public static String getProfil(Connection con, String mail, int port) {
        String recherche = "";
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM members WHERE email='" + mail + "' ");
            int nbr = 0;
            while (resultset.next()) {
                String nom = resultset.getString(resultset.findColumn("bdd"));
                String s = "_";
                String s2 = "-";
                String s1 = "" + nom;
                String rest = "";
                if (s.equals(s1)) {
                    //nothing rest = ".";
                } else if (s2.equals(s1)) {
                    //nothing  rest = ".";
                } else if (port == 0) {
                    //nothing  rest = ".";
                } else {
                    rest = nom;
                    MongoAPI m = new MongoAPI();
                    m.initClient("root", "1*smart-db*2", port, "admin");
                    float x = m.getDBSize(rest);
                    if (x <= 1000000) // ko
                    {
                        float y = x / 1000;
                        recherche += "<tr>\n"
                                + "                                <th>" + rest + "</th>\n"
                                + "                                <th>" + y + " ko</th> \n"
                                + "                                <th>" + port + "</th> \n"
                                + "                            </tr>";
                    } else if (x >= 1000000000) //Go
                    {
                        float y = x / 1000000000;
                        recherche += "<tr>\n"
                                + "                                <th>" + rest + "</th>\n"
                                + "                                <th>" + y + " Go</th> \n"
                                + "                                <th>" + port + "</th> \n"
                                + "                            </tr>";
                    } else //Mo
                    {
                        float y = x / 1000000;
                        recherche += "<tr>\n"
                                + "                                <th>" + rest + "</th>\n"
                                + "                                <th>" + y + " Mo</th> \n"
                                + "                                <th>" + port + "</th> \n"
                                + "                            </tr>";
                    }

                }

                nbr++;
            }
            if (nbr > 0) {
                recherche += "</SELECT>\n"
                        + "                            </div>\n"
                        + "                        </div>";
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    public static String getCapsule(Connection con, String mail) {
        String recherche = "";
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM capsules WHERE email='" + mail + "' ");
            int nbr = 0;
            while (resultset.next()) {
                
                String nom = resultset.getString(resultset.findColumn("nom"));
                System.out.println("********************************** "+nom);
                String taille = resultset.getString(resultset.findColumn("taille"));
                String date = resultset.getString(resultset.findColumn("date"));
                
                  recherche += "<tr>\n"
                                + "                                <th>" + nom + "</th>\n"
                                + "                                <th>" + taille + " ko</th> \n"
                                + "                                <th>" + date + "</th> \n"
                                + "                            </tr>";
               
                   
        }
        }catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    /**
     * Ajoute une BDD (à laquelle est assoicié une ip et un numéro de port) à un
     * client
     */
    public static Boolean addDbContainer(Connection con, String email, String ip, int vmid, int port, int taille) {
        Boolean notexist = false;
        try {
            Statement smt = con.createStatement();
            smt.executeUpdate("INSERT INTO `container`(`email`, `ip`,`vmid`, `port`, `taille`) VALUES ('" + email + "','" + ip + "','" + vmid + "','" + port + "','" + taille + "')");
            notexist = true;

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notexist;
    }

    /**
     * Retourne true si l'email existe. False sinon
     */
    public static Boolean existContainer(Connection con, String email) {
        Boolean exist = false;
        try {

            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM container WHERE email = '" + email + "'");
            if (resultset.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    public static String getIPContainer(Connection con, String mail) {
        String recherche = "";
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM container WHERE email='" + mail + "' ");
            int nbr = 0;
            while (resultset.next()) {
                recherche = resultset.getString(resultset.findColumn("ip"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    public static int getvmIDContainer(Connection con, String mail) {
        int recherche = 0;
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM container WHERE email='" + mail + "' ");
            int nbr = 0;
            while (resultset.next()) {
                String nom = resultset.getString(resultset.findColumn("vmid"));
                recherche = Integer.parseInt(nom);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    public static int getTailleContainer(Connection con, String mail) {
        int recherche = 0;
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM container WHERE email='" + mail + "' ");
            int nbr = 0;
            while (resultset.next()) {
                String nom = resultset.getString(resultset.findColumn("taille"));
                recherche = Integer.parseInt(nom);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    public static int getPortContainer(Connection con, String mail) {
        int recherche = 0;
        try {
            Statement smt = con.createStatement();
            ResultSet resultset = smt.executeQuery("SELECT * FROM container WHERE email='" + mail + "' ");
            int nbr = 0;
            while (resultset.next()) {
                String nom = resultset.getString(resultset.findColumn("port"));
                recherche = Integer.parseInt(nom);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recherche;
    }

    /**
     * Ce main sert uniquement de test, s'y référer pour voir comment appeler
     * les fonctions
     */
    public static void main(String[] args) {
        try {

            Connection c1 = DatabaseManager.connectionDatabase();
            /*
            ArrayList<Integer> listeID;
            listeID = listOfContainerID(c1);
            int i;
            for (i=0;i<listeID.size();i++){
               System.out.println(listeID.get(i));
            }
            
            DatabaseManager.addDbClient(c1, "samih@gmail.com", "nouvelleBDDSamih", "192.36.26.1", 2563);
            
            DatabaseManager.createClient(c1, "samih@gmail.com", "999999", 0, "bdddeSamih", "123.25.36.2", 25698);
            System.out.println("samih@gmail.com existe ? " + DatabaseManager.existClient(c1, "samih@gmail.com"));
            System.out.println("samih@gmail.com a 999999 comme mdp ? " + DatabaseManager.verifConnectionClient(c1, "samih@gmail.com", "999999"));
            System.out.println("mot de passe de Samih " + DatabaseManager.hisPassword(c1, "samih@gmail.com"));
            DatabaseManager.createClient(c1, "yann@gmail.com", "123456", 1, "bdddeYann", "123.25.36.25", 1234);
            DatabaseManager.createClient(c1, "yann@gmail.com", "123456", 1, "bdddeYann2", "123.25.36.25", 1234);
            DatabaseManager.addDbClient(c1, "yann@gmail.com" ,"nouvelleBDDYann", "192.36.26.1", 2563);
            System.out.println("yann@gmail.com existe ? " + DatabaseManager.existClient(c1, "yann@gmail.com"));
            DatabaseManager.deleteClient(c1, "yann@gmail.com");
            DatabaseManager.deleteDbClient(c1, "samih@gmail.com", "bdddeSamih");
            System.out.println("samih@gmail.com existe ? " + DatabaseManager.existClient(c1, "samih@gmail.com"));
             */
            c1.close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
