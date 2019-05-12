package com.kantin.dao;

import java.util.List;

import com.kantin.entity.KantinTransaksiEntity;

public interface KantinTransaksiDao {
	//void addTransaksi(KantinTransaksiEntity items);
    void addTransaksi(int id, int jumlah, int total);
    List<KantinTransaksiEntity> getAllTransaksi();
}
