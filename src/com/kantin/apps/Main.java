package com.kantin.apps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.kantin.controller.KantinTransaksiController;
import com.kantin.controller.RunnableThreadImpl;
import com.kantin.dao.Currency;
import com.kantin.dao.KantinMakananDao;
import com.kantin.dao.KantinTransaksiDao;
import com.kantin.entity.KantinMakananEntity;
import com.kantin.entity.KantinTransaksiEntity;
import com.kantin.entity.TransaksiTemporer;
import com.kantin.implement.KantinMakananImpl;
import com.kantin.implement.KantinTransaksiImpl;

public class Main {
	// input
	static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	static BufferedReader input = new BufferedReader(inputStreamReader);
	// get object
	private static KantinMakananEntity makanan = new KantinMakananEntity();
	private static KantinMakananDao makananDao = new KantinMakananImpl();
	private static KantinMakananImpl kantinMakananImpl = new KantinMakananImpl();
	private static KantinTransaksiDao transaksi = new KantinTransaksiImpl();
	private static Currency formatCurrency = new Currency();

	public static void main(String[] args) {
		showMenu();

	}

	private static void showMenu() {
		System.out.println("\n");
		System.out.println("\t\t Menu Utama");
		System.out.println("_______________________________________");
		System.out.println("");
		System.out.println("1. Daftar Makanan");
		System.out.println("2. Tambah Makanan");
		System.out.println("3. Transaksi");
		System.out.println("4. Rekap");
		System.out.println("5. Pencarian");
		System.out.println("6. Exmaple Thread");
		System.out.println("_______________________________________");
		System.out.print("Choose [1/2/3/4/5] : ");

		try {
			int choice = Integer.parseInt(input.readLine());
			switch (choice) {
			case 0: {
				System.exit(0);
				break;
			}
			case 1: {
				// daftar makanan
				printAllMakanan(makananDao.getAllMakanan());
				break;
			}
			case 2: {
				// tambah item
				tambahMakanan();
				break;
			}
			case 3: {
				getTransaksi();
				break;
			}
			case 4: {
				getReport();
				break;
			}
			case 5: {
				getSearch();
				break;
			}
			case 6: {
				RunnableThreadImpl threadImpl = new RunnableThreadImpl();
				Thread thread = new Thread(threadImpl, "MyThread");
				System.out.println("Main : " + Thread.currentThread().getName());
				thread.start();
				break;
			}
			default: {
				System.out.println("Pilihan salah!");
			}
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getSearch() {
		try {
			System.out.println();
			System.out.println("======================= Cari Data Makanan =========================");
			System.out.println("___________________________________________________________________");
			System.out.println();
			System.out.print("Masukan ID : ");
			int id = Integer.parseInt(input.readLine());
			makanan = kantinMakananImpl.getId(id);
			printSearchId(makanan);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.print("Not found!");
			showMenu();
		}
	}

	private static void printSearchId(KantinMakananEntity makanan) {
		System.out.println("\n");
		System.out.println("Result : ");
		System.out.println("| " + padRight("ID", 5) + "| " + padRight("Nama Makanan", 30) + "| " + padRight("Harga", 20)
				+ "|" + padRight("Stock", 10));
		System.out.println("-------------------------------------------------------------------");
		// Print Header Table
		System.out.println(
				"| " + padRight("" + makanan.getId_makanan(), 5) + "| " + padRight(makanan.getNama_makanan(), 30) + "| "
						+ padRight("" + formatCurrency.getFormat(makanan.getHarga_makanan()), 20) + "|"
						+ padRight("" + makanan.getStock(), 10));
		showMenu();
	}

	private static void getReport() {
		System.out.println();
		System.out.println("============================= Report Pendapatan ===========================");
		System.out.println("___________________________________________________________________________");

		// header
		System.out.println("|" + padRight("No", 5) + "|" + padRight("ID Makanan", 40) + "|" + padRight("Qty", 5) + "|"
				+ padRight("Total", 20) + "|");

		int total = 0;
		// loop as much cart
		int i = 1;
		for (KantinTransaksiEntity trans : transaksi.getAllTransaksi()) {

			// KantinMakananEntity makanan = transaksi.getById(trans.getId_makanan());

			System.out.println(
					"|" + padRight("" + (i++) + "", 5) + "|" + padRight(Integer.toString(trans.getId_makanan()), 40)
							+ "|" + padRight(Integer.toString(trans.getJumlah()), 5) + "|"
							+ padRight(formatCurrency.getFormat(trans.getTotal()), 20) + "|");

			total += trans.getTotal(); // get total

		}
		System.out.println(
				"|" + padRight("Total Pendapatan", 52) + "|" + padRight(formatCurrency.getFormat(total), 20) + "|");
		System.out.print("------------------------------------ Eof ----------------------------------");
		System.out.println("\n");
		showMenu();
	}

	public static void getTransaksi() {
		List<TransaksiTemporer> trans = new ArrayList<>();
		try {
			System.out.println();
			System.out.println("======================= Transaksi Makanan =========================");
			System.out.println("___________________________________________________________________");
			System.out.println();
			System.out.print("Masukan ID Makanan : ");
			int id_makanan = Integer.parseInt(input.readLine());
			System.out.print("Jumlah Pemesanan   : ");
			int jumlah = Integer.parseInt(input.readLine());

			TransaksiTemporer ins = new TransaksiTemporer();
			ins.setId_makanan(id_makanan);
			ins.setJumlah(jumlah);
			trans.add(ins);

			for (TransaksiTemporer row : trans) {
				// System.out.println(row.getId_makanan());
				KantinTransaksiController.insertTransaksi(row.getId_makanan(), row.getId_makanan(), row.getJumlah());
			}

			showMenu();
		} catch (Exception e) {
			System.out.print("This field is can't empty!");
			showMenu();
		}
	}

	private static void printAllMakanan(List<KantinMakananEntity> allMakanan) {
		try {
			System.out.println("\n");
			System.out.println("======================= Daftar Makanan =========================");
			System.out.println("________________________________________________________________");
			// Print Header Table
			System.out.println(
					"| " + padRight("No", 3) + "| " + padRight("ID Makanan", 10) + "| " + padRight("Nama Makanan", 20)
							+ "|" + padRight("Harga Makanan", 13) + "|" + padRight("Stock", 10) + "|");
			int i = 1;
			// looping
			for (KantinMakananEntity row : allMakanan) {
				System.out.println("| " + padRight("" + i, 3) + "| " + padRight("" + row.getId_makanan(), 10) + "| "
						+ padRight("" + row.getNama_makanan(), 20) + "|" + padRight("" +formatCurrency.getFormat(row.getHarga_makanan()), 13)
						+ "|" + padRight("" + row.getStock(), 10) + "|");

				// next row number
				i += 1;
			}
			System.out.println("________________________________________________________________");
			System.out.println("Successfully loaded!");
			showMenu();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.print("Not found!");
			showMenu();
		}
	}

	public static void tambahMakanan() {
		try {
			System.out.println("===================== Tambah Menu Makanan =========================");
			System.out.println("___________________________________________________________________");
			System.out.println();
			System.out.print("Masukan Nama Makanan : ");
			String name = input.readLine();
			System.out.print("Harga Makanan        : ");
			int harga = Integer.parseInt(input.readLine());
			System.out.print("Stock                : ");
			int stock = Integer.parseInt(input.readLine());

			KantinMakananEntity items = new KantinMakananEntity();
			items.setNama_makanan(name);
			items.setHarga_makanan(harga);
			items.setStock(stock);
			kantinMakananImpl.addMakanan(items);
			// showMenu
			showMenu();

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.print("This field is can't empty!");
			showMenu();
		}
	}

	public static String padRight(String inputString, int length) {
		return String.format("%1$-" + length + "s", inputString);
	}

}
