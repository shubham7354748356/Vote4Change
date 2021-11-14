/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.CandidateDao;
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
public class AddCandidateControllerServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession sess = request.getSession();
        String userid = (String)sess.getAttribute("userid");
        
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
       
        String candidate =request.getParameter("id");
        String usid = request.getParameter("uid");
        if(candidate!=null&&candidate.equals("getid"))
        {
            try
            {
                String id = CandidateDao.getId();
                out.println(id);
                return;
            }
            catch(SQLException sq)
            {
                RequestDispatcher rd = request.getRequestDispatcher("showexception.jsp");
                request.setAttribute("Exception", sq);
                rd.forward(request, response);
            }
        }
        else
        {
            try
            {
                boolean result = CandidateDao.isCandidatePresent(usid);
                System.out.println(result);
                String username;
                if(result)
                    username = "present";
                else
                   username =CandidateDao.getUserNameById(usid);
                ArrayList<String>city = CandidateDao.getCity();
                JSONObject obj = new JSONObject();
                StringBuffer sb = new StringBuffer();
                for(String x : city)
                {
                    sb.append("<option value='"+x+"'>"+x+"</option>");
                }
                if(username==null)
                    username = "wrong";
                obj.put("username" , username);
                obj.put("city" , sb.toString());
                out.println(obj);
                
            }
            catch(Exception sq)
            {
                RequestDispatcher rd = request.getRequestDispatcher("showexception.jsp");
                request.setAttribute("Exception", sq);
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
