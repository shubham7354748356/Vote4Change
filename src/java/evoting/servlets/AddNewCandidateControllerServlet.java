/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.servlets;

import evoting.dao.CandidateDao;
import evoting.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


/**
 *
 * @author india
 */
public class AddNewCandidateControllerServlet extends HttpServlet {

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
           DiskFileItemFactory df = new DiskFileItemFactory();
           ServletFileUpload sfu = new ServletFileUpload(df);
           ServletRequestContext srq = new ServletRequestContext(request);
           List<FileItem> multilist = sfu.parseRequest(srq);
          // System.out.println(multilist.toString());
           //System.out.println(multilist.size());
           ArrayList<String> al = new ArrayList<>();
           InputStream is=null;
           for(FileItem x : multilist)
           {
               if(x.isFormField())
               {
                   String fname = x.getFieldName();
                   String value = x.getString();
                   //System.out.println(fname+"    "+value);
                   al.add(value);
               }
               else
               {
                 //  System.out.println("we are in the else");
                   is = x.getInputStream();
                   String fname = x.getFieldName();
                   String name = x.getName();
                 //  System.out.println(fname+"   "+name);
               }
           }
          
           boolean re = CandidateDao.isPartyPresent(al.get(3), al.get(2));
           System.out.println("the re got "+re);
           if(re)
           {
               System.out.println("already present a candidate");
               rd = request.getRequestDispatcher("noseat.jsp");
           }
           
           else
           {
               System.out.println("no candidate relate to this seat");
           CandidateDTO ct = new CandidateDTO();
           ct.setCandidateid(al.get(0));
           ct.setUserid(al.get(1));
           ct.setParty(al.get(2));
           ct.setCity(al.get(3));
           ct.setSymbol(is);
          boolean result = CandidateDao.addCandidate(ct);
           if(result)
           {
               System.out.println("in the success jsp");
               rd = request.getRequestDispatcher("success.jsp");
           }
           else
           {
               rd = request.getRequestDispatcher("failure.jsp");
           }
           }
           
       }
           catch(Exception e)
            {
                    System.out.println("Exception in the add new candidate");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        System.out.println(e);
                        request.setAttribute("Exception", e);
                        rd = request.getRequestDispatcher("showexception.jsp");
                        
            }
       finally
       {
           rd.forward(request, response);;
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
