package com.kantin.implement;

import java.util.ArrayList;
import java.util.List;

import com.kantin.apps.Config;
import com.kantin.controller.KantinTransaksiController;
import com.kantin.dao.KantinMakananDao;
import com.kantin.dao.KantinTransaksiDao;
import com.kantin.entity.KantinMakananEntity;
import com.kantin.entity.KantinTransaksiEntity;

public class KantinTransaksiImpl implements KantinTransaksiDao{

	private static KantinTransaksiController transaksiController;
	private static KantinMakananDao makanan = new KantinMakananImpl();
	
	public KantinTransaksiImpl() {
		transaksiController = new KantinTransaksiController(Config.getConnection());
	}
	
	@Override
	public List<KantinTransaksiEntity> getAllTransaksi() {
		List<KantinTransaksiEntity> listAll = new ArrayList<>();
		try {
			listAll = transaksiController.getAllTransaksi();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listAll;
	}

	@Override
	public void addTransaksi(int id, int id_makanan, int jumlah) {
		// setter & getter transaksi
		KantinTransaksiEntity tran = new KantinTransaksiEntity();
		//panggil class dengan method "getById"
		KantinMakananEntity getId = makanan.getById(id);
		// set to tran
		tran.setId_makanan(getId.getId_makanan());
		tran.setJumlah(jumlah);
		tran.setTotal(jumlah * getId.getHarga_makanan());
		//save transaksi
		transaksiController.insertTransaksi(tran);
		//update stock
		makanan.updateMakanan(id,jumlah);
	}

}
