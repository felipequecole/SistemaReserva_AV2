/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Hotel;
import br.ufscar.dc.sistemareserva.dao.HotelDAO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author felipequecole
 */

@Named
@SessionScoped
public class NovoHotel implements Serializable {
    
    @Inject HotelDAO hoteldao; 
    Hotel dadosHotel;
    
        
    
}
