/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.CandidateDao;
import evoting.dao.VoteDao;
import evoting.dto.CandidateDetails;
import evoting.dto.PartyWiseResult;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
public class ElectionResultControllerServlet extends HttpServlet {

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
       RequestDispatcher rd=null;
       HttpSession session = request.getSession();
       String userid = (String)session.getAttribute("userid");
       if(userid==null)
       {
          session.invalidate();
          response.sendRedirect("accessdenied.html");
          return;
       }
       try
       {
           System.out.println("in the try of election result controller servlet");
           Map<String , Integer> result = VoteDao.getResult();
           System.out.println("fetched party and vote from voteDao");
           Set s = result.entrySet();
           Iterator it = s.iterator();
           LinkedHashMap<PartyWiseResult , Integer> resultDetails = new LinkedHashMap<>();
           while(it.hasNext())
           {
               Map.Entry<String  , Integer> e = (Map.Entry)it.next();
               System.out.println("no we are iterating with "+e.getKey());
               String symbol  = CandidateDao.getPartySymbol(e.getKey());
               System.out.println("got the symbol for "+e.getKey());
               PartyWiseResult pw = new PartyWiseResult();
               pw.setParty(e.getKey());
               pw.setSymbol(symbol);
               resultDetails.put(pw, e.getValue());
               
           }
           request.setAttribute("votecount", VoteDao.getVoteCount());
           request.setAttribute("result", resultDetails);
           rd = request.getRequestDispatcher("electionresult.jsp");
           System.out.println("attribute for show election result is setted successfully");
       }
       catch(Exception ex)
       {
           System.out.println(ex.getMessage());
           ex.printStackTrace();
           request.setAttribute("Exception", ex);
           rd = request.getRequestDispatcher("showexception.jsp");
       }
       finally
       {
           System.out.println("we are in the finally");
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
