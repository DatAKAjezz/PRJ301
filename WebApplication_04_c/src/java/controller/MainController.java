/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.Alert;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author datdat
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

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
         
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username.trim().length() == 0){
            out.print("Username cant be left empty!");
            return;
        }
        
        if (password.trim().length() == 0){
            out.print("Password cant be left empty!");
            return;
        }
        
                
        try{
          if (password.length() < 8) {
              throw new Exception("Mat khau phai co it nhat 8 ky tu");
          }
          if (username.equals("admin") && password.equals("12345678")){
              RequestDispatcher rd = request.getRequestDispatcher("search.html");
              rd.forward(request, response);
          }  
          else {
              response.sendRedirect("invalid.html");
              
               //    forward thường sẽ để các servlet giao tiếp với nhau, actión phía servers. Thường thì sẽ liên quan đến việc cho server tiếp tục xử lí nhiều công đoạn khác nhau.
               //    redirect thì sẽ dành cho phía client. Đơn giản vì họ không thể can thiệp về dữ liệu ở server. Việc của họ là sử dụng ui và chức năng phía FE.      
              
          }
        }
        catch (Exception e){
            out.print.("Error: " + e.getMessage());
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
