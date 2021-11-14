/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.RegistrationDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author india
 */
public class ShowUserControllerServlet extends HttpServlet {

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
           String username = (String)session.getAttribute("userid");
           if(username==null)
           {
               session.invalidate();
               response.sendRedirect("accessdenied.html");
               return;
           }
           try
           {
               
               String data = request.getParameter("data");
               String uid = request.getParameter("uid");
               System.out.println("data is coming with "+data+" and the uid is coming with "+uid);
               if(data!=null && uid==null && data.equalsIgnoreCase("getusername"))
               {
                   System.out.println("if is running");
                   ArrayList<String> al = RegistrationDao.getAllUserName();
                   StringBuffer sb = new StringBuffer();
                   sb.append("<option value=''>Select username</option>");
                   for(String x : al)
                   {
                       sb.append("<option value='"+x+"'>"+x+"</option>");
                   }
                   JSONObject json = new JSONObject();
                   json.put("username", sb.toString());
                   System.out.println("json is getting "+sb.toString());
                   out.println(json);
               }
               else if(data!=null && uid!=null && data.equalsIgnoreCase("getdetails"))
               {
                   System.out.println("else if is running");
                        UserDetails user = RegistrationDao.getUserByUsername(uid);
                        JSONObject json = new JSONObject();
                        json.put("uname", user.getUsername());
                        json.put("city", user.getCity());
                        json.put("address" , user.getAddress());
                        json.put("email", user.getEmail());
                        json.put("mobile", String.valueOf(user.getMobile()));
                        System.out.println(user.getAddress()+" "+user.getEmail()+" "+user.getMobile());
                        out.println(json);
                        
               }
               else if(data!=null  && data.equalsIgnoreCase("update") && uid!=null)
               {
                   
                   System.out.println("else if of the update is running");
                   UserDetails user = new UserDetails();
                   user.setAddress(request.getParameter("address"));
                   user.setUserid(uid);
                   user.setCity(request.getParameter("city"));
                   user.setEmail(request.getParameter("email"));
                   user.setMobile(Long.parseLong(request.getParameter("mobile")));
                   if(RegistrationDao.updateUser(user))
                       out.println("success");
                   else
                       out.println("failure");
               }
           }
           catch(SQLException sq)
           {
               sq.printStackTrace();
               request.setAttribute("Exception", sq);
               rd = request.getRequestDispatcher("showexception.jsp");
               rd.forward(request, response);
               System.out.println("some exception is occured");
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
