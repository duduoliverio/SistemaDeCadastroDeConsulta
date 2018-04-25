/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Consulta;
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
            + " (dataDoExame, ref_crm, ref_cpf)"
            + " (?,?,?)";

    private final static String BUSCAR_CONSULTA_SQL = "select"
            + " dataDoExame, ref_crm, ref_cpf"
            + " from Consulta";

    private final static String VALIDAR_SQL = "select"
            + " dataDoExame"
            + " from Consulta"
            + " where dataDoExame=?";

    DataSource dataSource;

    public ConsultaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Consulta gravarConsulta(Consulta c) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_CONSULTA_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setDate(1, new java.sql.Date(c.getDataConsulta().getTime()));
            ps.setString(2, c.getRef_crm());
            ps.setString(3, c.getRef_cpf());
            ps.execute();
        }
        return c;
    }

    public Boolean validarConsulta(String ref_crm, String ref_cpf, Date dataDoExame) throws SQLException {

        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(VALIDAR_SQL);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ps.setString(1, ref_crm);
        ps.setString(2, ref_cpf);
        ps.setString(3, sdf.format(dataDoExame));

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Data de consulta existente!");
            return false;
        }
        return true;

    }

    public List<Consulta> listarConsultasMedico(String ref_crm) throws SQLException {
        ArrayList<Consulta> ret = new ArrayList();
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_SQL);
        ps.setString(1, ref_crm);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Consulta consulta = new Consulta();
            consulta.setDataConsulta(rs.getDate("dataDoExame"));
            consulta.setRef_cpf(rs.getString("ref_cpf"));
            consulta.setRef_crm(rs.getString("ref_crm"));
            ret.add(consulta);
        }

        return (ret.isEmpty() ? null : ret);
    }

    public List<Consulta> listarConsultasPaciente(String ref_cpf) throws SQLException {
        ArrayList<Consulta> ret = new ArrayList();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_CONSULTA_SQL)) {

            ps.setString(1, ref_cpf);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setDataConsulta(rs.getDate("dataDoExame"));
                consulta.setRef_cpf(rs.getString("ref_cpf"));
                consulta.setRef_crm(rs.getString("ref_crm"));
                ret.add(consulta);
            }

            return (ret.isEmpty() ? null : ret);
        }

    }
}
