package com.kantin.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kantin.dao.KantinTransaksiDao;
import com.kantin.entity.KantinTransaksiEntity;
import com.kantin.implement.KantinTransaksiImpl;

public class KantinTransaksiController {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultset = null; 

	private static KantinTransaksiDao transaksi = new KantinTransaksiImpl();
	public KantinTransaksiController(Connection connection) {
		connect = connection;
	}

	public static void insertTransaksi(int id, int id_makanan, int jumlah) {
		transaksi.addTransaksi(id, id_makanan, jumlah);
	}

	public void insertTransaksi(KantinTransaksiEntity tran) {
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO tbl_transaksi (id_makanan,jumlah,total) " +
                    "VALUE (?, ?, ?)");
            preparedStatement.setInt(1, tran.getId_makanan());
            preparedStatement.setInt(2, tran.getJumlah());
            preparedStatement.setInt(3, tran.getTotal());
            preparedStatement.execute();

            System.out.println("Transaction success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<KantinTransaksiEntity> getAllTransaksi() {
		List<KantinTransaksiEntity> list = new ArrayList<>();
		try {
			preparedStatement = connect.prepareStatement("SELECT * FROM tbl_transaksi");
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				KantinTransaksiEntity trans = new KantinTransaksiEntity();
				trans.setId_transaksi(resultset.getInt("id_transaksi"));
				trans.setId_makanan(resultset.getInt("id_makanan"));
				trans.setJumlah(resultset.getInt("jumlah"));
				trans.setTotal(resultset.getInt("total"));
				list.add(trans);
			}
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
