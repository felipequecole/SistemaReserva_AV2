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
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

/**
 *
 * @author eduardo
 */

@Named
@RequestScoped
public class ListaSite implements Serializable {
    List<Site> listaSites;
    @Inject SiteDAO siteDAO;
    
    public List<Site> getListaSites(){
        return listaSites;
    }
    
    public void setListaSites(List<Site> listaSites){
        this.listaSites = listaSites;
    }
   
    public String verTodosSites() throws SQLException, NamingException{
        listaSites = siteDAO.listarTodosSites();
        return "listaSites";
    
    }
    
}
