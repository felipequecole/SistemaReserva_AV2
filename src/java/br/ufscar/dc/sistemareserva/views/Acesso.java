/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.dao.AdminDAO;
import br.ufscar.dc.sistemareserva.dao.HotelDAO;
import br.ufscar.dc.sistemareserva.dao.SiteDAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author felipequecole
 */
@Named
@SessionScoped
public class Acesso implements Serializable {
    @Inject AdminDAO adminDAO; 
    @Inject SiteDAO siteDAO;
    @Inject HotelDAO hotelDAO; 
    
    String username, senha, tipo; 

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String login(){
        switch (tipo) {
            case "admin":
                FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap().put("user", "admin");
                FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap().put("role", "admin");
                break;
            case "hotel":
                break;
            case "site":
                break;
            default:
                break;
        }
        return "index";
    }
    
    public String logout(){
        return "index";
    }
}
