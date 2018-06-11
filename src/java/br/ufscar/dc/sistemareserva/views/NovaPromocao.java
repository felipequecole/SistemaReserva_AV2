/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Promocao;
import br.ufscar.dc.sistemareserva.beans.Site;
import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
import br.ufscar.dc.sistemareserva.dao.SiteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class NovaPromocao implements Serializable {

    @Inject
    PromocaoDAO pdao;


    Promocao dadosPromocao;
    UIInput inputCnpj, inputUrl, inputData_fim, inputData_inicio;


    public UIInput getInputData_inicio() {
        return inputData_inicio;
    }

    public void setInputData_inicio(UIInput inputData_inicio) {
        this.inputData_inicio = inputData_inicio;
    }
    String mensagens = null;

    public String getMensagens() {
        return mensagens;
    }

    public void setMensagens(String mensagens) {
        this.mensagens = mensagens;
    }

    public UIInput getInputCnpj() {
        return inputCnpj;
    }

    public void setInputCnpj(UIInput inputCnpj) {
        this.inputCnpj = inputCnpj;
    }

    public UIInput getInputUrl() {
        return inputUrl;
    }

    public void setInputUrl(UIInput inputUrl) {
        this.inputUrl = inputUrl;
    }

    public UIInput getInputData_fim() {
        return inputData_fim;
    }

    public void setInputData_fim(UIInput inputData_fim) {
        this.inputData_fim = inputData_fim;
    }

    public NovaPromocao() throws SQLException {
        dadosPromocao = new Promocao();
    }


    public Promocao getDadosPromocao() {
        return dadosPromocao;
    }

    public void setDadosPromocao(Promocao dadosPromocao) {
        this.dadosPromocao = dadosPromocao;
    }

    public String gravaPromocao() throws SQLException {
        System.out.println("no grava:" + dadosPromocao.getUrl());
        Promocao p = pdao.gravaPromocao(dadosPromocao);
        

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Promoção cadastrada"));
        return "index?faces-redirect=true";
    }

    public void validaPreco(FacesContext context, UIComponent toValidate, String value) {
        if (Float.parseFloat(value) <= 0) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Preço deve ser maior que zero.");
            context.addMessage(toValidate.getClientId(context), message);
        }

    }

    public void validaUrl(FacesContext context, UIComponent toValidate, String value) throws ParseException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);

        System.out.println(value);

        // ver se esse cu cagado tá dando certo
        if (!pdao.validaPromocao((String) this.inputCnpj.getValue(), value, (Date) inputData_inicio.getValue())) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Já existe uma promoção cadastrada para esse mesmo dia com esse mesmo site.");
            context.addMessage(toValidate.getClientId(context), message);
        }

    }

    public void validaDataFim(FacesContext context, UIComponent toValidate, String value) throws ParseException, SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date dfim = (Date) sdf.parse(value);

        if (dfim.before((Date) inputData_inicio.getValue())) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message_2 = new FacesMessage("Data de fim deve ser depois da data de início.");
            context.addMessage(toValidate.getClientId(context), message_2);
        }
    }



    /*
    * Falta validações:
        - Data de inicio > Data fim
        - Preco < 0
        - Mesmo site, mesmo hotel, mesma data
     */
}
