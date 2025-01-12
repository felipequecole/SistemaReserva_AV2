/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.sistemareserva.dao;

import br.ufscar.dc.sistemareserva.beans.Promocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;

/**
 *
 * @author felipequecole
 */
@RequestScoped
public class PromocaoDAO {

    private final static String CRIAR_PROMOCAO_SQL = "insert into promocao "
            + "(url, cnpj, data_inicio, data_fim, preco) values "
            + "(?,?,?,?,?)";

    private final static String BUSCA_PROMOCAO_POR_HOTEL_SQL = "select "
            + "url, cnpj, data_inicio, data_fim, preco, id "
            + "from promocao "
            + "where cnpj=?";

    private final static String BUSCA_PROMOCAO_POR_SITE_SQL = "select "
            + "url, cnpj, data_inicio, data_fim, preco, id "
            + "from promocao "
            + "where url=?";

    private final static String VALIDA_SQL = "select "
            + "url "
            + "from promocao "
            + "where url=? and cnpj=? and data_inicio=?";

    @Resource(name = "jdbc/SistemaReservaDBLocal")
    DataSource datasource;

    public Promocao gravaPromocao(Promocao promocao) throws SQLException {

        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_PROMOCAO_SQL, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, promocao.getUrl());
            ps.setString(2, promocao.getCnpj());
            ps.setDate(3, new java.sql.Date(promocao.getData_inicio().getTime()));
            ps.setDate(4, new java.sql.Date(promocao.getData_fim().getTime()));
            ps.setFloat(5, promocao.getPreco());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                promocao.setId(rs.getInt(1));
            }

        }

        return promocao;
    }

    public Boolean validaPromocao(String cnpj, String url, Date data_inicio) throws SQLException {

        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(VALIDA_SQL);) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            ps.setString(1, url);
            ps.setString(2, cnpj);
            ps.setString(3, df.format(data_inicio));
            System.out.println(df.format(data_inicio));

            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    System.out.println("ACHOU UMA IGUAL, URL:" + rs.getString("url"));
                    return false;
                }
            }

        }

        return true;
    }

    public List<Promocao> listaPromocaoHotel(String cnpj) throws SQLException {
        ArrayList<Promocao> ret = new ArrayList();
        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCA_PROMOCAO_POR_HOTEL_SQL);) {
            ps.setString(1, cnpj);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Promocao promo = new Promocao();
                    promo.setCnpj(rs.getString("cnpj"));
                    promo.setData_fim(rs.getDate("data_fim"));
                    promo.setData_inicio(rs.getDate("data_inicio"));
                    promo.setUrl(rs.getString("url"));
                    promo.setId(rs.getInt("id"));
                    promo.setPreco(rs.getFloat("preco"));
                    ret.add(promo);
                }
            }

        }

        return (ret.isEmpty() ? null : ret);
    }

    public List<Promocao> listaPromocaoSite(String url) throws SQLException {
        ArrayList<Promocao> ret = new ArrayList();
        try (Connection con = datasource.getConnection();
                PreparedStatement ps = con.prepareStatement(BUSCA_PROMOCAO_POR_SITE_SQL);) {
            ps.setString(1, url);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Promocao promo = new Promocao();
                    promo.setUrl(rs.getString("url"));
                    promo.setCnpj(rs.getString("cnpj"));
                    promo.setData_fim(rs.getDate("data_fim"));
                    promo.setData_inicio(rs.getDate("data_inicio"));
                    promo.setId(rs.getInt("id"));
                    promo.setPreco(rs.getFloat("preco"));
                    ret.add(promo);
                }
            }

        }

        return (ret.isEmpty() ? null : ret);
    }
}
