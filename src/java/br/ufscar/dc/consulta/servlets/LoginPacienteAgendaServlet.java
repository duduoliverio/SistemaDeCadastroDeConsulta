/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.servlets;

import br.ufscar.dc.consulta.beans.Paciente;
import br.ufscar.dc.consulta.dao.PacienteDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author duduoliverio
 */
@WebServlet(name = "LoginPacienteAgendaServlet", urlPatterns = {"/LoginPacienteAgendaServlet"})
public class LoginPacienteAgendaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Resource(name = "jdbc/ConsultaDBLocal")
    DataSource dataSource;
    
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
            PacienteDAO pdao = new PacienteDAO(dataSource);
            Paciente paciente = null;
            try {
                paciente = pdao.buscarPaciente(cpf);
                if (paciente == null) {
                    request.getSession().setAttribute("login_mensagem", "Login Inválido!");
                    response.sendRedirect("loginPacienteAgenda.jsp");
                } else if (paciente.getSenha().equals(senha)) {
                    request.getSession().setAttribute("cpf", paciente.getCpf());
                    response.sendRedirect("consultaForm.jsp");
                } else {
                    request.getSession().setAttribute("login_mensagem", "Login Inválido!");
                    response.sendRedirect("loginPacienteAgenda.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensagem", e.getLocalizedMessage());
                request.getRequestDispatcher("erro.jsp").forward(request, response);
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
