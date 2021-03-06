/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.servlets;

import br.ufscar.dc.consulta.beans.Medico;
import br.ufscar.dc.consulta.beans.Paciente;
import br.ufscar.dc.consulta.dao.MedicoDAO;
import br.ufscar.dc.consulta.dao.PacienteDAO;
import br.ufscar.dc.consulta.forms.NovaConsultaFormBean;
import br.ufscar.dc.consulta.forms.NovoMedicoFormBean;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author duduoliverio
 */
@WebServlet(name = "NovaConsultaServlet", urlPatterns = {"/NovaConsultaServlet"})
public class NovaConsultaServlet extends HttpServlet {

    @Resource(name = "jdbc/ConsultaDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        NovaConsultaFormBean ncfb = new NovaConsultaFormBean();
        try {
            BeanUtils.populate(ncfb, request.getParameterMap());
            request.getSession().setAttribute("novaConsulta", ncfb);
            List<String> mensagens = ncfb.validar();
            if (mensagens == null) {
                request.getRequestDispatcher("confirmarConsulta.jsp").forward(request, response);
            } else {
                request.setAttribute("mensagens", mensagens);
                request.getRequestDispatcher("consultaForm.jsp").forward(request, response);
            }

        } catch (Exception e) {
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
