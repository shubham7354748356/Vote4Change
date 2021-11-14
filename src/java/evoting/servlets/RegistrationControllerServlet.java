/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.RegistrationDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author india
 */
public class RegistrationControllerServlet extends HttpServlet {

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
       String username = request.getParameter("username");
       String user_id = request.getParameter("user_id");
       long mobile = Long.parseLong(request.getParameter("mobile"));
       String city = request.getParameter("city");
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       String address = request.getParameter("address");
       String gender = request.getParameter("gender");
       System.out.println("the gender is =>"+gender);
     UserDetails user = new UserDetails();  
     user.setAddress(address);
     user.setCity(city);
     user.setEmail(email);
     user.setMobile(mobile);
     user.setUserid(user_id);
     user.setUsername(username);
     user.setPassword(password);
     user.setGender(gender);
     String result=null;
     String error=null;
     RequestDispatcher rd=null;
     try
     {
         if(RegistrationDao.searchUser(user_id))
         {
             result="uap";
         }
         else
         {
             RegistrationDao.registerUser(user);
         }
         rd = request.getRequestDispatcher("Registration.jsp");
         request.setAttribute("result", result);
         request.setAttribute("error", error);
         
         
     }
     catch(SQLException sq)
     {
         error=sq.getMessage();
         System.out.println(sq.getMessage());
         rd = request.getRequestDispatcher("showException.jsp");
         request.setAttribute("Exception", sq);
     }
     finally
     {
         rd.forward(request, response);
     }
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
        processRequest(request, response);
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
