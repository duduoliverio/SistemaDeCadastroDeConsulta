/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.servlets;

import br.ufscar.dc.consulta.beans.Consulta;
import br.ufscar.dc.consulta.dao.ConsultaDAO;
import br.ufscar.dc.consulta.forms.NovaConsultaFormBean;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.NamingException;
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
@WebServlet(name = "GravarConsultaServlet", urlPatterns = {"/GravarConsultaServlet"})
public class GravarConsultaServlet extends HttpServlet {

    @Resource(name = "jdbc/ConsultaDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException, SQLException, NamingException {

        NovaConsultaFormBean ncfb = (NovaConsultaFormBean) request.getSession().getAttribute("novaConsulta");
        request.getSession().removeAttribute("novaConsulta");

        ConsultaDAO cdao = new ConsultaDAO(dataSource);

        // Verifica se o paciente já tem uma consulta para a data informada
        try {
            if (cdao.buscarPacienteConsulta(ncfb.getRef_cpf(), ncfb.getDataConsulta())) {
                request.setAttribute("mensagem", "O paciente já possuí consulta para a data informada.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.getStackTrace();
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

        // Verifica se o médico já tem uma consulta para a data informada
        try {
            if (cdao.buscarMedicoConsulta(ncfb.getRef_crm(), ncfb.getDataConsulta())) {
                request.setAttribute("mensagem", "O médico já possuí consulta para a data informada.");
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.getStackTrace();
            request.setAttribute("mensagem", "Teste" + e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataConsulta = null;
        try {
            dataConsulta = sdf.parse(ncfb.getDataConsulta());
        } catch (ParseException e) {
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        try {
            Consulta c = new Consulta();
            c.setRef_crm(ncfb.getRef_crm());
            c.setRef_cpf(ncfb.getRef_cpf());
            c.setDataConsulta(dataConsulta);
            c = cdao.gravarConsulta(c);
            request.setAttribute("mensagem", "Consulta agendada!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Teste" + e.getLocalizedMessage());
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
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GravarConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(GravarConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(GravarConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(GravarConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(GravarConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(GravarConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
