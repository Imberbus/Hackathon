/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samih
 */
public class LoginInterface extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                   
        RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        session = request.getSession();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        
        Connection c1 = null;
        try {
            c1 = DatabaseManager.connectionDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //on se connecte avec son email
        if(DatabaseManager.existID(c1, username, password)) //si il existe un id dans la table student, on est redirige vers le profil etudiant
        {
            session.setAttribute( "mail", username);
            session.setAttribute( "pwd", password);
            //<=========ICI
            Connection con = null;
            try {
                con = DatabaseManager.connectionDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(DelBddServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int port = DatabaseManager.hisPortDB(con, username);
            String profil=DatabaseManager.getProfil(con,username,port);
            request.setAttribute("profil", profil); 
            //RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            //rd.forward(request, response);
            response.sendRedirect("/SmartDB-war/home");

            System.out.println("Fermeture des objets connection");
            if (c1 != null) {
                try {
                    c1.close();
                } catch (SQLException ignore) {
                }
            }            
        }

        else
        {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }   
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
