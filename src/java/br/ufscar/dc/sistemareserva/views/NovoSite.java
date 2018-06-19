/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Site;
import br.ufscar.dc.sistemareserva.dao.SiteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author eduardo
 */

@Named
@SessionScoped
public class NovoSite implements Serializable {
    @Inject SiteDAO siteDAO;
    Site dadosSite;
    
    
    public NovoSite(){
        dadosSite = new Site();
    
    }

    public Site getDadosSite() {
        return dadosSite;
    }

    public void setDadosSite(Site dadosSite) {
        this.dadosSite = dadosSite;
    }
    
        public String gravarSite() throws SQLException, NamingException {


        Site s = siteDAO.gravarSite(dadosSite);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Site cadastrado"));
        
        return "index?faces-redirect=true";
    }
        
    public void validarTelefone(FacesContext context, UIComponent toValidate, String value) {

        
        if (value.matches("[a-zA-Z]+")) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Telefone não deve conter letras");
            context.addMessage(toValidate.getClientId(context), message);
        }

    }
    
        public void validarSenha(FacesContext context, UIComponent toValidate, String value) {

        
        if (value.length() < 6) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("A senha deve conter no mínimo 6 caracteres");
            context.addMessage(toValidate.getClientId(context), message);
        }

    }
}
