/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Admin;
import br.ufscar.dc.sistemareserva.beans.Hotel;
import br.ufscar.dc.sistemareserva.beans.Site;
import br.ufscar.dc.sistemareserva.dao.AdminDAO;
import br.ufscar.dc.sistemareserva.dao.HotelDAO;
import br.ufscar.dc.sistemareserva.dao.SiteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author felipequecole
 */
@Named
@SessionScoped
public class Acesso implements Serializable {
    @Inject AdminDAO adao; 
    @Inject SiteDAO sdao;
    @Inject HotelDAO hdao; 
    
    private String username, senha, tipo, messages = null; 

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
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
    
    public String login() throws SQLException{
        this.setMessages(null);
        switch (tipo) {
            case "admin":
                Admin admin = adao.buscaAdmin(this.getUsername());
                if (admin != null){
                    if (admin.getSenha().equals(this.getSenha())){
                         FacesContext.getCurrentInstance().
                                getExternalContext().getSessionMap().
                                 put("user", this.getUsername());
                    FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap().
                            put("role", "admin");
                    return "index?faces-redirect=true";                    
                    }
                }
                this.setMessages("Usuário ou senha inválidos." );
                return "login?faces-redirect=true";
            case "hotel":
                Hotel hotel = hdao.buscaHotel(this.getUsername());
                if (hotel != null){
                    if (hotel.getSenha().equals(this.getSenha())){
                        FacesContext.getCurrentInstance().
                                getExternalContext().getSessionMap().
                                 put("user", hotel.getNome());
                    FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap().
                            put("role", "hotel");
                    return "index?faces-redirect=true";
                    }
                }
                this.setMessages("Usuário ou senha inválidos." );
                return "login?faces-redirect=true";
            case "site":
                Site site = sdao.buscarSite(this.getUsername());
                if (site != null){
                    if (site.getSenha().equals(this.getSenha())){
                        FacesContext.getCurrentInstance().
                                getExternalContext().getSessionMap().
                                 put("user", site.getNome());
                    FacesContext.getCurrentInstance().
                        getExternalContext().getSessionMap().
                            put("role", "site");
                    return "index?faces-redirect=true";
                    }
                }
                this.setMessages("Usuário ou senha inválidos." );
                return "login?faces-redirect=true";
            default:
                this.setMessages("Ops, algo deu errado. Tente novamente");
                return "login?faces-redirect=true";
        }
    }
    
    public String logout(){
        this.setMessages(null); 
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}
