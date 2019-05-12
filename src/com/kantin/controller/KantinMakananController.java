package com.kantin.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.kantin.entity.KantinMakananEntity;



public class KantinMakananController{
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public KantinMakananController(Connection connection) {
		connect = connection;
	}
	
	public List<KantinMakananEntity>  getAllMakanan() throws Exception {
		List<KantinMakananEntity> list = new ArrayList<>();
		try {
			
			preparedStatement = connect.prepareStatement("select * from tbl_makanan");
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				KantinMakananEntity makanan = new KantinMakananEntity();
				makanan.setId_makanan(resultSet.getInt("id_makanan"));
				makanan.setNama_makanan(resultSet.getString("nama_makanan"));
				makanan.setHarga_makanan(resultSet.getInt("harga_makanan"));
				makanan.setStock(resultSet.getInt("stock"));
				// add to list
				list.add(makanan);
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
//				if (connect != null) {
//					connect.close();
//				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return list;
	}
	
	public void insertItems(KantinMakananEntity items){
		try {
            preparedStatement = connect.prepareStatement("INSERT INTO tbl_makanan (nama_makanan,harga_makanan,stock) " +
                    "VALUE (?, ?, ?)");
            preparedStatement.setString(1, items.getNama_makanan());
            preparedStatement.setInt(2, items.getHarga_makanan());
            preparedStatement.setInt(3, items.getStock());
            preparedStatement.execute();

            System.out.println("Successfully added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public KantinMakananEntity getDetail(int id) {
		KantinMakananEntity makanan = new KantinMakananEntity();
		try {
            preparedStatement = connect.prepareStatement("SELECT * FROM tbl_makanan WHERE id_makanan=? ");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
            	makanan.setId_makanan(resultSet.getInt("id_makanan"));
            	makanan.setNama_makanan(resultSet.getString("nama_makanan"));
            	makanan.setHarga_makanan(resultSet.getInt("harga_makanan"));
            	makanan.setStock(resultSet.getInt("stock"));
				
			}
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return makanan;
	}

	public void updateStock(KantinMakananEntity stock) {
		try {
			preparedStatement = connect.prepareStatement("UPDATE tbl_makanan SET stock=? WHERE id_makanan=? ");
			preparedStatement.setInt(1, stock.getStock());
			preparedStatement.setInt(2, stock.getId_makanan());
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public KantinMakananEntity getIdDetail(int id) {
		KantinMakananEntity result = null;
		try {
			List<KantinMakananEntity> list = getAllMakanan();
			for (KantinMakananEntity row : list) {
				if (row.getId_makanan() == id) {
					result  = row;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
