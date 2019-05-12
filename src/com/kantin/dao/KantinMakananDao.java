package com.kantin.dao;

import java.util.List;

import com.kantin.entity.KantinMakananEntity;

public interface KantinMakananDao {

	List<KantinMakananEntity> getAllMakanan();
	void addMakanan(KantinMakananEntity items);
	KantinMakananEntity getById(int id);	
	void updateMakanan(int id, int jumlah);
	KantinMakananEntity getId(int id);
}
