package com.kantin.entity;

public class KantinMakananEntity {
	private int id_makanan;
	private String nama_makanan;
	private int harga_makanan;
	private int stock;

	public int getId_makanan() {
		return id_makanan;
	}

	public void setId_makanan(int id_makanan) {
		this.id_makanan = id_makanan;
	}

	public String getNama_makanan() {
		return nama_makanan;
	}

	public void setNama_makanan(String nama_makanan) {
		this.nama_makanan = nama_makanan;
	}

	public int getHarga_makanan() {
		return harga_makanan;
	}

	public void setHarga_makanan(int harga_makanan) {
		this.harga_makanan = harga_makanan;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
