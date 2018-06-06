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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    String cidadePesquisa;
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

    public String getCidadePesquisa() {
        return cidadePesquisa;
    }

    public void setCidadePesquisa(String cidadePesquisa) {
        this.cidadePesquisa = cidadePesquisa;
    }

    public String listarPorCidade(String cidade) throws SQLException, NamingException {
        listaHotel = hotelDao.listaTodosHoteisCidade(cidade);
        return "listaHotel";
    }

    public String listarPorCidade() {
        return "index?faces-redirect=true";
    }

    @PostConstruct
    public void init(){
        try {
            listaHotel = hotelDao.listaTodosHoteis();
        } catch (SQLException ex) {
            Logger.getLogger(ListaHotel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ListaHotel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
