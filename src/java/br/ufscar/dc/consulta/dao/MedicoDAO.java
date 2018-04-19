/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author duduoliverio
 */
public class MedicoDAO {

    private final static String CRIAR_MEDICO_SQL = "insert into Medico"
            + " (crm, nome, senha, especialidade)"
            + " values (?,?,?,?)";

    private final static String LISTAR_MEDICO_SQL = "select"
            + " id as medicoId, crm, nome, especialidade"
            + " from Medico";

    private final static String LISTAR_MEDICO_POR_ESPECIALIDADE_SQL = "select"
            + " id as medicoId, crm, nome, especialidade"
            + " from Medico"
            + " where especialidade = ?";
    
    private final static String BUSCAR_MEDICO_SQL = "select"
            + " Crm, Senha"
            + " from Medico"
            + " where Crm=?";

    DataSource dataSource;

    public MedicoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Medico gravarMedico(Medico m) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_MEDICO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, m.getCrm());
            ps.setString(2, m.getNome());
            ps.setString(3, m.getSenha());
            ps.setString(4, m.getEspecialidade());
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                m.setId(rs.getInt(1));
            }
        }
        return m;
    }

    public List<Medico> listarTodosMedicos() throws SQLException, NamingException {
        List<Medico> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_MEDICO_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("medicoId"));
                    m.setCrm(rs.getString("crm"));
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    ret.add(m);
                }
            }
        }
        return ret;
    }

    public List<Medico> listarTodosMedicosPorEspecialidade(String especialidade) throws SQLException {
        List<Medico> ret = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(LISTAR_MEDICO_POR_ESPECIALIDADE_SQL)) {
            ps.setString(1, especialidade);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico m = new Medico();
                    m.setId(rs.getInt("medicoId"));
                    m.setCrm(rs.getString("crm"));
                    m.setNome(rs.getString("nome"));
                    m.setEspecialidade(rs.getString("especialidade"));
                    ret.add(m);
                }
            }
        }
        return ret;
    }

    public Medico buscarMedico(String crm) throws SQLException {
        Medico medico = new Medico();
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(BUSCAR_MEDICO_SQL);
        ps.setString(1, crm);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            medico.setCrm(rs.getString("crm"));
            medico.setSenha(rs.getString("senha"));
        } else {
            return null;
        }
        return medico;
    }
    
}
