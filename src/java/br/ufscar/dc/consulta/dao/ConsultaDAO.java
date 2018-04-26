/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Consulta;
import br.ufscar.dc.consulta.forms.NovaConsultaFormBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author duduoliverio
 */
public class ConsultaDAO {
    
    private final static String CRIAR_CONSULTA_SQL = "insert into Consulta"
            + " (ref_crm, ref_cpf, dataDoExame)"
            + " values (?,?,?)";

    private final static String BUSCAR_CONSULTA_MEDICO_SQL = "select"
            + " c.ref_crm, c.ref_cpf, c.dataDoExame"
            + " from consulta c"
            + " inner join medico m on m.crm = c.ref_crm"
            + " inner join paciente p on p.cpf = c.ref_cpf"
            + " where c.ref_crm=?";
    
    private final static String BUSCAR_CONSULTA_PACIENTE_SQL = "select"
            + " c.ref_crm, c.ref_cpf, c.dataDoExame"
            + " from consulta c"
            + " inner join medico m on m.crm = c.ref_crm"
            + " inner join paciente p on p.cpf = c.ref_cpf"
            + " where c.ref_cpf=?";
    
    private final static String VERIFICA_CONSULTA_PACIENTE_SQL = "select"
            + " COUNT(*) as total"
            + " from Consulta"
            + " where dataDoExame = ?"
            + " and ref_cpf=?";
    
    private final static String VERIFICA_CONSULTA_MEDICO_SQL = "select"
            + " COUNT(*) as total"
            + " from Consulta"
            + " where dataDoExame = ?"
            + " and ref_crm=?";

    DataSource dataSource;

    public ConsultaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Consulta gravarConsulta(Consulta c) throws SQLException, NamingException {
        java.sql.Date data = null;
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_CONSULTA_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setDate(1, new java.sql.Date(c.getDataConsulta().getTime()));
            ps.setString(2, c.getRef_crm());
            ps.setString(3, c.getRef_cpf());
            ps.execute();
        }
        return c;
    }

    public Boolean buscarPacienteConsulta(String ref_cpf, String dataConsulta) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(VERIFICA_CONSULTA_PACIENTE_SQL)) {
            ps.setString(1, dataConsulta.trim());
            ps.setString(2, ref_cpf.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                if(rs.getInt("total") > 0)
                   return true;
                return false;
            } catch (Exception e) {
                e.getStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
    
    public Boolean buscarMedicoConsulta(String ref_crm, String dataConsulta) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(VERIFICA_CONSULTA_MEDICO_SQL)) {
            ps.setString(1, dataConsulta.trim());
            ps.setString(2, ref_crm.trim());

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                if(rs.getInt("total") > 0)
                   return true;
                return false;
            } catch (Exception e) {
                e.getStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    public List<NovaConsultaFormBean> buscarConsultaMedico(String Ref_crm) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_MEDICO_SQL)) {
            ps.setString(1, Ref_crm);
            
            List<NovaConsultaFormBean> ret = new ArrayList<>();
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NovaConsultaFormBean c = new NovaConsultaFormBean();
                    c.setRef_crm(rs.getString("ref_crm"));
                    c.setRef_cpf(rs.getString("ref_cpf"));
                    c.setDataConsulta(rs.getString("data"));
                    ret.add(c);
                }
                return ret;
            }
        }
    }
    
    public List<NovaConsultaFormBean> buscarConsultaPaciente(String ref_cpf) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_PACIENTE_SQL)) {
            ps.setString(1, ref_cpf);
            System.out.println("CPF: " + ref_cpf );
            List<NovaConsultaFormBean> ret = new ArrayList<>();
            
            try (ResultSet rs = ps.executeQuery()) {
                     
                while (rs.next()) {
                    NovaConsultaFormBean c = new NovaConsultaFormBean();
                    c.setRef_crm(rs.getString("ref_crm"));
                    c.setRef_cpf(rs.getString("ref_cpf"));
                    c.setDataConsulta(rs.getString("data"));
                    ret.add(c);
                }
                return ret;
            }
        }
    }
}
