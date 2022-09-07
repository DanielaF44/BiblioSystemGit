package com.example.bibliosystem.batch;

import com.example.bibliosystem.Exemplaire;
import com.example.bibliosystem.Pret;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PretMapper implements RowMapper<Pret> {
    @Override
    public Pret mapRow(ResultSet rs, int rowNum) throws SQLException{
        Pret pret = new Pret();
        pret.setPret_id(rs.getInt("id"));
        pret.setDateDebut(rs.getDate("date_debut"));
        pret.setDateFin(rs.getDate("date_fin"));
        pret.setRenouvele(rs.getBoolean("renouvele"));
        pret.setExemplaireId((Exemplaire) rs.getObject("exemplaire_id"));
        return pret;

    }
}
