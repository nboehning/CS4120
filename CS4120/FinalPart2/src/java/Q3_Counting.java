
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nboeh
 */
@WebServlet(urlPatterns = {"/Q3_Counting"})
public class Q3_Counting extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Q3_Counting</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Q3_Counting at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
                
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Q3_Counting</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Counting</h1>");
            out.println("<form method='post'>");
            out.println("<textarea name='in' rows='4' cols='50'></textarea><br><br>");
            out.println("<button type='submit' value='Submit'>Count</button>");
            out.println("<button type='reset' value='Reset'>Reset</button>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
        
        String textAreaText = request.getParameter("in");
        
        String[] words = textAreaText.split("\\s+");
        String[] lines = textAreaText.split("\n");
        int numChars = countChars(textAreaText);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Q3_Counting</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Counting</h1>");
            out.println("<p>Number of words in text area: " + words.length + "</p>");
            out.println("<p>Number of lines in text area: " + lines.length + "</p>");
            out.println("<p>Number of characters in text area: " + numChars + "</p>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    int countChars(String in) {
        int toReturn = 0;
        for(int i = 0; i < in.length(); i++)
            if(!Character.isWhitespace(in.charAt(i)))
                toReturn++;
                
        return toReturn;
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
