package com.kantin.implement;

import java.util.ArrayList;
import java.util.List;

import com.kantin.apps.Config;
import com.kantin.controller.KantinMakananController;
import com.kantin.dao.KantinMakananDao;
import com.kantin.entity.KantinMakananEntity;

public class KantinMakananImpl implements KantinMakananDao{

	private static KantinMakananController makananController;
	
	public KantinMakananImpl() {
		makananController = new KantinMakananController(Config.getConnection());
	}

	@Override
	public List<KantinMakananEntity> getAllMakanan() {
		List<KantinMakananEntity> list = new ArrayList<>();
		try {
			list = makananController.getAllMakanan();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addMakanan(KantinMakananEntity items) {
		makananController.insertItems(items);
	}

	
	@Override
	public KantinMakananEntity getById(int id) {
		return makananController.getDetail(id);
	}

	@Override
	public void updateMakanan(int id, int jumlah) {
		KantinMakananEntity lama = getById(id);
		KantinMakananEntity newStock = new KantinMakananEntity();
		newStock.setId_makanan(lama.getId_makanan());
		newStock.setStock(lama.getStock() - jumlah);
		
		makananController.updateStock(newStock);
	}

	@Override
	public KantinMakananEntity getId(int id) {
		return makananController.getIdDetail(id);
	}

}
