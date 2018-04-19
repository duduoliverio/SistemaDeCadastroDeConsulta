package teste;

import br.ufscar.dc.consulta.beans.Medico;
import br.ufscar.dc.consulta.beans.Paciente;
import br.ufscar.dc.consulta.dao.MedicoDAO;
import br.ufscar.dc.consulta.dao.PacienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "TestarDAO", urlPatterns = {"/TestarDAO"})
public class TestarDAO extends HttpServlet {
    
    @Resource(name="jdbc/ConsultaDBLocal")
    DataSource dataSource;

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
        
        PacienteDAO pdao = new PacienteDAO(dataSource);
        Paciente p = new Paciente();
        p.setNome("Felipe Augusto");
        p.setCpf("12345678925");
        p.setSenha("456789");
        p.setSexo("Masculino");
        p.setTelefone("16998457652");
        p.setDataDeNascimento(new Date());
        
        Paciente p2 = null;

        try {
            p2 = pdao.gravarPaciente(p);
        } catch (SQLException ex) {
            Logger.getLogger(TestarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TestarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MedicoDAO mdao = new MedicoDAO(dataSource);
        
        Medico m = new Medico();
        m.setCrm("123456/SP");
        m.setNome("César Ferreira");
        m.setSenha("098767");
        m.setEspecialidade("Ginecologista");
            
        Medico m2 = null;

        try {
            m2 = mdao.gravarMedico(m);
        } catch (SQLException ex) {
            Logger.getLogger(TestarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(TestarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestarDAO</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestarDAO at " + request.getContextPath() + "</h1>");
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
