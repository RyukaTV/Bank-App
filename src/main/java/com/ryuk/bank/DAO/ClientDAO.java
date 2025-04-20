package com.ryuk.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ryuk.bank.DTO.ClientDTO;
import com.ryuk.bank.tools.DataBaseLinker;


public class ClientDAO {
	
	private static final Connection db = DataBaseLinker.getConnexion();
	
	public static final ClientDTO getUserbyLoginandMdp(final String login, final String mdp) {
		try {
			final PreparedStatement state = db.prepareStatement("select * from client where id= ? and hashed_password= SHA2(?,256)");
			state.setString(1, login);
			state.setString(2, mdp);
			final ResultSet results = state.executeQuery();
			ClientDTO dto = null;
			if (results.next()) {
				dto = new ClientDTO();
				dto.setId(results.getString("id"));
				dto.setNom(results.getString("nom"));
				dto.setPrenom(results.getString("prenom"));
				dto.setHashed_password(results.getString("hashed_password"));
				dto.setEmail(results.getString("email"));
				dto.setDate_creation(results.getTimestamp("date_creation"));
			}
			return dto;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
