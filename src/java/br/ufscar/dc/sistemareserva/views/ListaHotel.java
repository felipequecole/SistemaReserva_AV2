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
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author frankson
 */
@Named
@RequestScoped
public class ListaHotel implements Serializable {

    @Inject
    HotelDAO hotelDao;
    List<Hotel> listaHotel;

    public List<Hotel> getListaHotel() {
        return listaHotel;
    }

    public void setListaHotel(List<Hotel> listaHotel) {
        this.listaHotel = listaHotel;
    }

    public String verTodosHoteis() throws SQLException, NamingException {
        listaHotel = hotelDao.listaTodosHoteis();
        return "listaHotel";
    }
}
