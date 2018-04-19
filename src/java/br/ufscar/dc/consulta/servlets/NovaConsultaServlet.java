/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.servlets;

import br.ufscar.dc.consulta.beans.Consulta;
import br.ufscar.dc.consulta.forms.ConsultaFormBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, ParseException, InvocationTargetException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        ConsultaFormBean cfb = new ConsultaFormBean();
        List<String> mensagens = new ArrayList<String>();
        String cpf = (String) request.getSession().getAttribute("ref_cpf"); 
            BeanUtils.populate(cfb, request.getParameterMap());
            mensagens = cfb.Validar(dataSource, cpf);
            request.getSession().setAttribute("novaConsulta", cfb);
            Consulta consulta = new Consulta();
            consulta.getRef_cpf();
            System.out.println(consulta.getRef_cpf());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                consulta.setRef_cpf(cfb.getRef_cpf());
                consulta.setRef_crm(cfb.getRef_crm());
                consulta.setDataConsulta(sdf.parse(cfb.getDataDoExame()));
                if (mensagens != null) {
                    request.setAttribute("mensagem", mensagens);
                    request.getSession().setAttribute("novaConsulta", cfb);
                    request.getRequestDispatcher("consultaForm.jsp").forward(request, response);
                 }
                } 
                catch (Exception e) {
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
         try {
             processRequest(request, response);
         } catch (ParseException ex) {
             Logger.getLogger(NovaConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InvocationTargetException ex) {
             Logger.getLogger(NovaConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             Logger.getLogger(NovaConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
             processRequest(request, response);
         } catch (ParseException ex) {
             Logger.getLogger(NovaConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InvocationTargetException ex) {
             Logger.getLogger(NovaConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             Logger.getLogger(NovaConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
