/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.servlets;

import br.ufscar.dc.consulta.beans.Paciente;
import br.ufscar.dc.consulta.dao.PacienteDAO;
import br.ufscar.dc.consulta.forms.NovoPacienteFormBean;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "GravarPacienteServlet", urlPatterns = {"/GravarPacienteServlet"})
public class GravarPacienteServlet extends HttpServlet {

    @Resource(name = "jdbc/ConsultaDBLocal")
    DataSource dataSource;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NovoPacienteFormBean npfb = (NovoPacienteFormBean) request.getSession().getAttribute("novoPaciente");
        request.getSession().removeAttribute("novoPaciente");

        PacienteDAO pdao = new PacienteDAO(dataSource);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = null;
        try {
            dataNascimento = sdf.parse(npfb.getDataDeNascimento());
        } catch (ParseException e) {
            request.setAttribute("mensagem", e.getLocalizedMessage());
            request.getRequestDispatcher("erro.jsp").forward(request, response);
        }
        try {
            Paciente p = new Paciente();
            p.setCpf(npfb.getCpf());
            p.setNome(npfb.getNome());
            p.setSenha(npfb.getSenha());
            p.setTelefone(npfb.getTelefone());
            p.setSexo(npfb.getSexo());
            p.setDataDeNascimento(dataNascimento);
            p = pdao.gravarPaciente(p);
            request.setAttribute("mensagem", "Obrigado pelo cadastro!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
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
