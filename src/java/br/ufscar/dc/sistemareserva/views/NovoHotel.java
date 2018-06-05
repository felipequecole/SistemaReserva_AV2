/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Hotel;
import br.ufscar.dc.sistemareserva.dao.HotelDAO;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author felipequecole
 */
@Named
@SessionScoped
public class NovoHotel implements Serializable {

    @Inject
    HotelDAO hotelDao;
    Hotel dadosHotel;

    public NovoHotel() {
        dadosHotel = new Hotel();
    }

    public Hotel getDadosHotel() {
        return dadosHotel;
    }

    public void setDadosHotel(Hotel dadosHotel) {
        this.dadosHotel = dadosHotel;
    }

    public String gravarHotel() throws SQLException, NamingException {

        Hotel h = hotelDao.gravaHotel(dadosHotel);
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hotel cadastrado"));
        
        return "index?faces-redirect=true";
    }
}
