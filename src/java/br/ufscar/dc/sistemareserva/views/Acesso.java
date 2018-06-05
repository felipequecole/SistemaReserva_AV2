/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author felipequecole
 */
@Named
@SessionScoped
public class Acesso implements Serializable {
    
    public String login(){
        return "index";
    }
    
    public String logout(){
        return "index";
    }
}
