/*
 * 
 * 
 * 
 */

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
@WebServlet(urlPatterns = {"/Exercise37_5"})
public class Exercise37_5 extends HttpServlet {

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
            out.println("<title>Servlet Exercise37_5</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Exercise37_5 at " + request.getContextPath() + "</h1>");
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
            out.println("<title>Servlet Exercise37_5</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Compute Loan Payment</h1>");
            out.println("<form method='post'>");
            out.println("Loan Amount");
            out.println("<input type='number' name='loanAmt'><br>");            
            out.println("Annual Interest Rate");
            out.println("<input type='number' step='0.01' name='rate'><br>");
            out.println("Number of Years");
            out.println("<input type='number' name='years'><br>");
            out.println("<button type='submit' value='Submit'>Compute Loan Payment</button>");
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
        
        double loanAmt = Double.parseDouble(request.getParameter("loanAmt"));
        double intRate = Double.parseDouble(request.getParameter("rate"));
        int years = Integer.parseInt(request.getParameter("years"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Exercise37_5</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Compute Loan Payment</h1>");
            out.println("<p>Starting loan amount: " + loanAmt + "</p>");
            out.println("<p>Annual Interest Rate: " + intRate + "</p>");
            out.println("<p>Number of years: " + years + "</p>");
            out.println("<p>Monthly Loan Payment: " + getMonthlyPayment(intRate, loanAmt, years) + "</p>");
            out.println("<p>Total Loan Payment: " + getTotalPayment(getMonthlyPayment(intRate, loanAmt, years), years) + "</p>");
            out.println("</body>");
            out.println("</html>");
        }        
    }
    
    double getMonthlyPayment(double rate, double amt, int yrs) {
        double monthlyInterestRate = rate / 1200;
        double monthlyPayment = amt * monthlyInterestRate / (1 -
            (1 / Math.pow(1 + monthlyInterestRate, yrs * 12)));
        return monthlyPayment;
    }
    
    double getTotalPayment(double monthPay, int yrs) {
        return monthPay * yrs * 12;
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
