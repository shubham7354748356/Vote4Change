package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class managecandidate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n");
      out.write("    <link href=\"stylesheet/admin.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"stylesheet/backgroundimage.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"stylesheet/pageheader.css\" rel=\"stylesheet\">\n");
      out.write("    <script type=\"text/javascript\" src=\"jsscript/jquery.js\"></script>\n");
      out.write("     <script type=\"text/javascript\" src=\"jsscript/dynamic.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"jsscript/adminoptions.js\"></script>\n");
      out.write("    <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\n");
      out.write("    <script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n");
      out.write("    <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n");
      out.write("   \n");
      out.write("        \n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("   \n");
      out.write("    \n");
      out.write("    <div class=\"sticky\">\n");
      out.write("        <div class=\"candidate\">VOTE FOR CHANGE</div><br>\n");
      out.write("        <div id=\"div1\" style=\"text-align: right\"><a href=\"login.html\">LOGOUT</a></div>\n");
      out.write("        <div class=\"subcandidate\">Login Page</div>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"class1\" onclick=\"fun()\" style=\"position:absolute;top:200px;right:1200px;\">\n");
      out.write("        <image style=\"height:200px;width:300px;\" src=\"images/addcandidate.png\"/>\n");
      out.write("        <h3>Add Candidate</h3>\n");
      out.write("      </div>\n");
      out.write("     <div id=\"class2\"  onclick=\"showcandidate()\" style=\"position:absolute;top:200px;right:800px;\">\n");
      out.write("        <image style=\"height:200px;width:300px;\" src=\"images/update1.jpg\"/>\n");
      out.write("        <h3>Update Candidate</h3>\n");
      out.write("            \n");
      out.write("        \n");
      out.write("    </div>\n");
      out.write("     <div id=\"class3\"  onclick=\"managecandidate()\" style=\"position:absolute;top:200px;right:400px;\">\n");
      out.write("        <image style=\"height:200px;width:300px;\" src=\"images/candidate.jpg\"/>\n");
      out.write("        <h3>Show Candidate</h3>\n");
      out.write("            \n");
      out.write("        \n");
      out.write("    </div>\n");
      out.write("     <div id=\"class4\" onclick=\"managecandidate()\">\n");
      out.write("        <image  src=\"images/update3.jpg\"/>\n");
      out.write("        <h3>Delete Candidate</h3>\n");
      out.write("            \n");
      out.write("        \n");
      out.write("    </div>\n");
      out.write("    <div id=\"result\" style=\"position: absolute;top: 500px;left:300px;\"></div>\n");
      out.write("    <div id=\"addres\"></div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
