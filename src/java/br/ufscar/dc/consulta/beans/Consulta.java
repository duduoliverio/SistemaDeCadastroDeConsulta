/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.beans;

import java.util.Date;

/**
 *
 * @author duduoliverio
 */
public class Consulta {
    
    private Date dataConsulta;
    private String ref_crm, ref_cpf;

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

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

}