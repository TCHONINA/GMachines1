package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import beans.Machine;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class SalleService implements IDao<Salle> {

	@Override
	public boolean create(Salle o) {
	
		String req=" insert into salle values (null,?,?)";
			try {
				PreparedStatement ps= Connexion.getInstane().getConnection().prepareStatement(req);
				ps.setString(1, o.getCode());
				ps.setString(2, o.getType());
				 if (ps.executeUpdate() == 1) {
		                return true;
		            }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}

	@Override
	public boolean delete(Salle o) {
		  String sql = "delete from salle where id  = ?";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setInt(1, o.getId());
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("delete : erreur sql : " + e.getMessage());

	        }
	        return false;
		
	}

	@Override
	public boolean update(Salle o) {
		 String sql = "update salle set code  = ? ,type = ? where id  = ?";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setString(1, o.getCode());
	            ps.setString(2, o.getType());
	            ps.setInt(3, o.getId());
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("update : erreur sql : " + e.getMessage());

	        }
	        return false;
	}

	@Override
	public Salle findById(int id) {
		 String sql = "select * from salle where id  = ?";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                return new Salle(rs.getInt("id"), rs.getString("code"), rs.getString("type")
	                        );
	            }

	        } catch (SQLException e) {
	            System.out.println("findById " + e.getMessage());
	        }
	        return null;
	}

	@Override
	public List<Salle> findAll() {
		  List<Salle> salles = new ArrayList<Salle>();

	        String sql = "select * from salle";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                salles.add(new Salle(rs.getInt("id"), rs.getString("code"), rs.getString("type")
	                       ));
	            }

	        } catch (SQLException e) {
	            System.out.println("findAll " + e.getMessage());
	        }
	        return salles;
	}
	
	
	public List<Salle> CountSalle() {
		  List<Salle> salles = new ArrayList<Salle>();

	        String sql = "select ,count() as count from salle";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                salles.add(new Salle(rs.getInt("id"), rs.getString("code"), rs.getString("type"),rs.getInt("count")
	                       ));
	            }

	        } catch (SQLException e) {
	            System.out.println("findAll " + e.getMessage());
	        }
	        return salles;
	}
	public boolean login(String username,String password) {
		try {
	         String sql = "select id,username,password from user where username = ? and password= ?" ;
	         PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	         ps.setString(1, username);
	         ps.setString(2, password);
	         ResultSet rs = ps.executeQuery();
	         
	    if(rs.next()) {
	    	
	    	return true;
	    	
	    }else {
	    	
	    	return false;
	    }
		}catch (SQLException e) {
			
			System.out.println( e.getMessage());
		}
	    return false;
	  }


}
