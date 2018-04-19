/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.dao;

import br.ufscar.dc.consulta.beans.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author duduoliverio
 */
public class AdminDAO {

    private final static String BUSCAR_ADMIN_SQL = "select "
            + "usuario, senha "
            + "from administrador "
            + "where usuario=?";

    DataSource dataSource;

    public AdminDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Admin buscaAdmin(String usuario) throws SQLException {
        Admin admin = new Admin();
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement(BUSCAR_ADMIN_SQL);
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            admin.setUsuario(rs.getString("usuario"));
            admin.setSenha(rs.getString("senha"));
        } else {
            return null;
        }
        return admin;
    }
}