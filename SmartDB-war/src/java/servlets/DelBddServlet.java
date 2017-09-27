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
import mongocontroller.MongoAPI;

/**
 *
 * @author yann
 */
public class DelBddServlet extends HttpServlet {

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
        if (request.getSession().getAttribute("mail") == null) {
            response.sendRedirect("/SmartDB-war/");
        } else {
            processRequest(request, response);
            Connection con = null;
            try {
                con = DatabaseManager.connectionDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(DelBddServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String entreprise=DatabaseManager.getBDD(con, (String) request.getSession().getAttribute("mail"));
            request.setAttribute("entreprise", entreprise); // This will be available as ${entreprise}
            RequestDispatcher rd = request.getRequestDispatcher("delBdd.jsp");
            rd.forward(request, response);
        }
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
             
        Connection c1 = null;
            try {
                c1 = DatabaseManager.connectionDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(RegisterInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
         
               
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        session = request.getSession();
       String nom=request.getParameter("Bdd");
        String email= (String) request.getSession().getAttribute("mail");
        DatabaseManager.deleteCapsule(c1, email, nom);
           
                String profil=DatabaseManager.getCapsule(c1, email);
                request.setAttribute("profil", profil); 
                RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
            }             
        }       
        
               
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
   


