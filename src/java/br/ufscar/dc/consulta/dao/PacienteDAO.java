/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author duduoliverio
 */
public class PacienteDAO {

    private final static String CRIAR_PACIENTE_SQL = "insert into Paciente"
            + " (Cpf, Nome, Senha, Telefone, Sexo, dataDeNascimento)"
            + " values (?,?,?,?,?,?)";

    private final static String BUSCAR_PACIENTE_SQL = "select"
            + " Cpf, Senha"
            + " from Paciente"
            + " where Cpf=?";

    DataSource dataSource;

    public PacienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Paciente gravarPaciente(Paciente p) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_PACIENTE_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, p.getCpf());
            ps.setString(2, p.getNome());
            ps.setString(3, p.getSenha());
            ps.setString(4, p.getTelefone());
            ps.setString(5, p.getSexo());
            ps.setDate(6, new java.sql.Date(p.getDataDeNascimento().getTime()));
            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                p.setId(rs.getInt(1));
            }

        }
        return p;
    }

    /*public Paciente buscarPaciente(int id) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCAR_PACIENTE_SQL)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setCpf(rs.getString("cpf"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setTelefone(rs.getString("telefone"));
                p.setSexo(rs.getString("sexo"));
                p.setDataDeNascimento(new Date(rs.getDate("dataDeNascimento").getTime()));
                return p;
            }
        }
    }*/
    
    public Paciente buscarPaciente(String cpf) throws SQLException {
        Paciente paciente = new Paciente();
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(BUSCAR_PACIENTE_SQL);
        ps.setString(1, cpf);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            paciente.setCpf(rs.getString("cpf"));
            paciente.setSenha(rs.getString("senha"));
        } else {
            return null;
        }
        return paciente;
    }
}
