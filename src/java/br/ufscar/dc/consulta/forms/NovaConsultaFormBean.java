/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duduoliverio
 */
public class NovaConsultaFormBean {

    private String ref_crm, ref_cpf, dataConsulta;

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

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public List<String> validar() {
        List<String> mensagens = new ArrayList<String>();
        if (ref_crm.trim().length() == 0) {
            mensagens.add("O CRM não pode ser vazio!");
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
            sdf.parse(dataConsulta);
        } catch (ParseException pe) {
            mensagens.add("Data da consulta não é valida!");
        }

        return (mensagens.isEmpty() ? null : mensagens);
    }

}
