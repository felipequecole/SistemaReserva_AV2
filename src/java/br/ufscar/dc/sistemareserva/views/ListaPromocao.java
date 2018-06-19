    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.views;

import br.ufscar.dc.sistemareserva.beans.Promocao;
import br.ufscar.dc.sistemareserva.dao.PromocaoDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ListaPromocao implements Serializable{
    @Inject
    PromocaoDAO pdao;
    private List<Promocao> listaPromocao;

    public List<Promocao> getListaPromocao() {
        return listaPromocao;
    }

    public void setListaPromocao(List<Promocao> listaPromocao) {
        this.listaPromocao = listaPromocao;
    }
    
    public String verPromocoesHotel(String cnpj) throws SQLException{
        listaPromocao = pdao.listaPromocaoHotel(cnpj);
        return "listaPromocao";
    }
    
    public String verPromocoesSite(String url) throws SQLException{
        listaPromocao = pdao.listaPromocaoSite(url);
        return "listaPromocao";
    }
}
