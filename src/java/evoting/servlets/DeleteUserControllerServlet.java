/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.CandidateDao;
import evoting.dao.RegistrationDao;
import evoting.dao.VoteDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author india
 */
public class DeleteUserControllerServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
           HttpSession session = request.getSession();
           RequestDispatcher rd=null;
           String userid = (String)session.getAttribute("userid");
           if(userid==null)
           {
               session.invalidate();
               response.sendRedirect("accessdenied.html");
               return;
           }
           try
           {
               String u = request.getParameter("data");
               String task = request.getParameter("task");
               System.out.println("task is coming with the value =>"+task);
               System.out.println("u is comig with the value => "+u);
               if(task==null && u!=null)
                {
                    UserDetails user = RegistrationDao.getUserByUsername(u);
                    request.setAttribute("details", user);
                    
               }
               else if(task!=null && u!=null && task.equalsIgnoreCase("delete"))
               {
                   System.out.println("######we are in the else if");
                   System.out.println(u);
                   System.out.println(task);
                   if( VoteDao.deleteCandidate(u) && CandidateDao.deleteCandidateByUserid(u) && RegistrationDao.deleteUser(u))
                       request.setAttribute("result" , "success");
                   else
                       request.setAttribute("result", "failed");
                   
                       
               }
               rd = request.getRequestDispatcher("deleteuserresponse.jsp");
               
               
           }
           catch(SQLException sq)
           {
               sq.printStackTrace();
               request.setAttribute("Exception", sq);
               rd = request.getRequestDispatcher("showexception.jsp");
           }
           finally
           {
               rd.forward(request, response);
           }
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
