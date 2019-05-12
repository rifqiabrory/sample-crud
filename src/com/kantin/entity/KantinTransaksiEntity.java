package com.kantin.entity;

public class KantinTransaksiEntity {

	private int id_transaksi;
	private int id_makanan;
	private int jumlah;
	private int total;

	public int getId_transaksi() {
		return id_transaksi;
	}

	public void setId_transaksi(int id_transaksi) {
		this.id_transaksi = id_transaksi;
	}

	public int getId_makanan() {
		return id_makanan;
	}

	public void setId_makanan(int id_makanan) {
		this.id_makanan = id_makanan;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


}
