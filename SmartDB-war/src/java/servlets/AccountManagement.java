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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samih
 */
@WebServlet(name = "AccountManagement", urlPatterns = {"/account"})
public class AccountManagement extends HttpServlet {

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
            try {
                Connection con = DatabaseManager.connectionDatabase();
                if (DatabaseManager.isVipClient(con, (String) request.getSession().getAttribute("mail")) == 0) {
                    request.setAttribute("acctype", "Free");
                } else {
                    request.setAttribute("acctype", "Premium");
                }
                con.close();
                RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
                rd.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AccountManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        session = request.getSession();        
        String usermail = (String) session.getAttribute("mail"); // récupération du mail du client
        System.out.println("ici...."+usermail);
        if (request.getParameter("changemail") != null && request.getParameter("pwd") != null && request.getParameter("newmail") != null) {
            try {
                Connection con = DatabaseManager.connectionDatabase();
                if (DatabaseManager.hisPassword(con, usermail).equals(request.getParameter("pwd"))) {
                    DatabaseManager.modifMail(con, usermail, request.getParameter("newmail"));
                    session.setAttribute( "mail", request.getParameter("newmail")); 
                    usermail = (String) session.getAttribute("mail");
                    System.out.println("ici.1..."+usermail);
                } else {
                    //traitement 
                }
                con.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountManagement.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("changepwd") != null) {
            try {
                Connection con = DatabaseManager.connectionDatabase();
                if (DatabaseManager.hisPassword(con, usermail).equals(request.getParameter("oldpwd"))) {
                    DatabaseManager.modifPwd(con, usermail, request.getParameter("newpwd"));
                } else {
                    //traitement error
                }
                con.close();

            } catch (SQLException ex) {
                Logger.getLogger(AccountManagement.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
        RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
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
