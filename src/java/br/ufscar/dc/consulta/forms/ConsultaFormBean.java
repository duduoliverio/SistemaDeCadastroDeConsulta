/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.forms;

import br.ufscar.dc.consulta.dao.ConsultaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.sql.DataSource;

/**
 *
 * @author duduoliverio
 */
public class ConsultaFormBean {
    
    private String ref_crm, ref_cpf, dataDoExame;

    public String getRef_crm() {
        return ref_crm;
    }

    public void setRef_crm(String ref_crm) {
        this.ref_crm = ref_crm;
    }

    public String getRef_cpf() {
        return ref_cpf;
    }

    public void setRef_cpf(String ref_cpf) {
        this.ref_cpf = ref_cpf;
    }

    public String getDataDoExame() {
        return dataDoExame;
    }

    public void setDataDoExame(String dataDoExame) {
        this.dataDoExame = dataDoExame;
    }

      
    public List<String> Validar(DataSource dataSource, String cpf) {
        List<String> mensagens = new ArrayList<String>();
        ConsultaDAO cdao = new ConsultaDAO(dataSource);

        if (ref_crm.trim().length() == 0) {
            mensagens.add("O endereço não pode ser vazio!");
        }
        if (ref_cpf.contains(".")) {
            mensagens.add("CPF não pode conter pontos!");
        }
        if (ref_cpf.contains("-")) {
            mensagens.add("CPF não pode conter traço!");
        }
        if (ref_cpf.trim().length() == 0) {
            mensagens.add("CPF não pode ser vazio");
        }
             
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.parse(dataDoExame);
        } catch (ParseException pe) {
            mensagens.add("Data do exame não é valida!");
        }
        
        return (mensagens.isEmpty() ? null : mensagens);
    }
    
}
