package com.ryuk.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryuk.bank.DTO.CompteBancaireDTO;
import com.ryuk.bank.tools.DataBaseLinker;

public class CompteBancaireDAO {
	
	private static final Connection db = DataBaseLinker.getConnexion();
	
	public static final List<CompteBancaireDTO> getComptesByUserId(final String userId) {
		try {
			final PreparedStatement state = db.prepareStatement("select * from compte_bancaire where client_id= ? and status= 'actif'");
			state.setString(1, userId);
			final ResultSet results = state.executeQuery();
			final List<CompteBancaireDTO> list= new ArrayList<CompteBancaireDTO>();
			while (results.next()) {
				final CompteBancaireDTO dto = new CompteBancaireDTO();
				dto.setId(results.getString("id"));
				dto.setNumeroCompte(results.getString("numero_compte"));
				dto.setClientId(results.getString("client_id"));
				dto.setTypeCompte(results.getString("type_compte"));
				dto.setSolde(results.getDouble("solde"));
				dto.setDateOuverture(results.getTimestamp("date_ouverture"));
				dto.setStatus(results.getString("status"));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
