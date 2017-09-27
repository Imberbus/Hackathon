/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.*;
import static database.DatabaseManager.addDbContainer;
import static database.DatabaseManager.existContainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mongocontroller.MongoAPI;
import ssh.Controller;

/**
 *
 * @author yann
 */
public class AddBddServlet extends HttpServlet {
    Container cont= new Container(); 
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
            RequestDispatcher rd = request.getRequestDispatcher("addBdd.jsp");
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
        
            Connection c1 = null;
            try {
                c1 = DatabaseManager.connectionDatabase();
            } catch (SQLException ex) {
                Logger.getLogger(RegisterInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            processRequest(request, response);
            
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            session = request.getSession();
        
            String date=request.getParameter("date").toString();       
            String nameBdd=request.getParameter("nameBdd");
            
            DatabaseManager.createCapsule(c1, request.getSession().getAttribute("mail").toString(),nameBdd, "0 Gb", date);
            
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);
           
        
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
