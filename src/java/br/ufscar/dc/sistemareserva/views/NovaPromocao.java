/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Hotel;
import br.ufscar.dc.sistemareserva.beans.Promocao;
import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author eduardo
 */
@Named
@SessionScoped
public class NovaPromocao implements Serializable{
    
    @Inject 
    PromocaoDAO pdao;   
    
    Promocao dadosPromocao;
    
    public NovaPromocao(){
        dadosPromocao = new Promocao();
    }

    public Promocao getDadosPromocao() {
        return dadosPromocao;
    }

    public void setDadosPromocao(Promocao dadosPromocao) {
        this.dadosPromocao = dadosPromocao;
    }
    
    public String gravaPromocao() throws SQLException{
        
        Promocao p = pdao.gravaPromocao(dadosPromocao);
        

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Promoção cadastrada"));
        return "index?faces-redirect=true";
    }
    
    /*
    * Falta validações:
        - Data de inicio > Data fim
        - Preco < 0
        - Mesmo site, mesmo hotel, mesma data
    */
    
    
}
