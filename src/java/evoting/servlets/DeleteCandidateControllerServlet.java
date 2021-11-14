/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.CandidateDao;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DeleteCandidateControllerServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
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
           String candidateid = request.getParameter("candidateid");
           String task = request.getParameter("task");
           if(task!=null && candidateid==null && task.equalsIgnoreCase("getid"))
           {
               ArrayList<String> al = CandidateDao.getCandidateId();
               StringBuffer sb = new StringBuffer();
               sb.append("<option>CHOOSE ID:</option>");
               for(String x : al)
               {
                   sb.append("<option value='"+x+"'>"+x+"</<option>");
               }
               out.println(sb.toString());
           }
           else if(task==null && candidateid!=null)
           {
               CandidateDetails cd = CandidateDao.getDetailsById(candidateid);
               StringBuffer displayBlock = new StringBuffer();
               
                String str = "<img src='data:image/jpg;base64,"+cd.getSymbol()+"' style='width:300px;height:200px'>";
        displayBlock.append("<table>");
        displayBlock.append("<tr><th>User Id:<th><td>"+cd.getUserid()+"</td></tr>");
         displayBlock.append("<tr><th>Candidate Name:<th><td>"+cd.getCandidateName()+"</td></tr>");
          displayBlock.append("<tr><th>City:<th><td>"+cd.getCity()+"</td></tr>");
           displayBlock.append("<tr><th>Party:<th><td>"+cd.getParty()+"</td></tr>");
            displayBlock.append("<tr><th>Symbol:<th><td>"+str+"</td></tr>");
            displayBlock.append("<tr><th><input type='button' value='delete candidate' onclick='confirmdelete()'/></th></tr>");
            displayBlock.append("</table>");
               JSONObject json = new JSONObject();
               json.put("subdetails", displayBlock.toString());
           System.out.println(displayBlock);
           out.println(json);
           }
           else if(task!=null && candidateid!=null && task.equalsIgnoreCase("delete"))
           {
               boolean result = CandidateDao.deleteCandidate(candidateid);
               if(result) 
                   out.println("success");
               else
                   out.println("failed");
           }
                    }
           catch(Exception sq)
           {
               sq.printStackTrace();
               request.setAttribute("Exception", sq);
               rd = request.getRequestDispatcher("showexception.jsp");
               
           }
           finally
           {
               rd.forward(request , response);
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
