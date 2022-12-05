package database;

import java.util.ArrayList;

import builder.KosBuilder;
import model.Kos;

public class KosDatabase {
	private ArrayList<Kos> kosDatabase;
	private static KosDatabase instance;
	
	public static KosDatabase getInstance() {
		if(instance == null) instance = new KosDatabase();
		
		return instance;
	}
	
	private KosDatabase() {
		kosDatabase = new ArrayList<>();
		
		addDummyData();
	}
	
	public void addKos(Kos kos) {
		kosDatabase.add(kos);
	}
	
	public Kos getKosById(String id) {
		for (Kos kos : kosDatabase) {
			if(kos.getId().equals(id)) return kos;
		}
		
		return null;
	}
	
	public boolean deleteKosById(String id) {
		for (int i = 0; i < kosDatabase.size(); i++) {
			if(kosDatabase.get(i).getId().equals(id)) {
				kosDatabase.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public int getLength() {
		return kosDatabase.size();
	}
	
	public void printAllKos() {
		if(kosDatabase.size() == 0) System.out.println("\nNo Data\n");
		else {
			System.out.println("==========================================");
			for (Kos kos : kosDatabase) {
				System.out.println("Kos Id : " + kos.getId());
				System.out.println("Nama Kos : " + kos.getNama());
				System.out.println("Jenis Kos : " + kos.getJenis());
				System.out.println("Alamat Kos : " + kos.getAlamat());
				System.out.println("Harga Kos : " + kos.getHarga());
				System.out.println("-----------------------");
				System.out.println("Ukuran Kamar (dalam Meter) : " + kos.getPanjangKamar() + " x " + kos.getLebarKamar());
				System.out.println("Kamar Mandi : " + kos.getKamarMandi());
				System.out.println("Kasur : " + (kos.isKasur() ? "Ada" : "-"));
				System.out.println("Lemari : " + (kos.isLemari() ? "Ada" : "-"));
				System.out.println("Meja Kursi : " + (kos.isMejaKursi() ? "Ada" : "-"));
				System.out.println("AC : " + (kos.isAc() ? "Ada" : "-"));
				System.out.println("Air Panas : " + (kos.isAirPanas() ? "Ada" : "-"));
				System.out.println("Listrik : " + (kos.isListrik() ? "Termasuk" : "-"));
				System.out.println("Laundry : " + (kos.isLaundry() ? "Termasuk" : "-"));
				System.out.println("==========================================");
			}
		}
	}
	
	public void printUserKos(String userId) {
		if(kosDatabase.size() == 0) System.out.println("\nNo Data\n");
		else {
			System.out.println("==========================================");
			for (Kos kos : kosDatabase) {
				if(kos.getUserId().equals(userId)) {
					System.out.println("Kos Id : " + kos.getId());
					System.out.println("Nama Kos : " + kos.getNama());
					System.out.println("Jenis Kos : " + kos.getJenis());
					System.out.println("Alamat Kos : " + kos.getAlamat());
					System.out.println("Harga Kos : " + kos.getHarga());
					System.out.println("-----------------------");
					System.out.println("Ukuran Kamar (dalam Meter) : " + kos.getPanjangKamar() + " x " + kos.getLebarKamar());
					System.out.println("Kamar Mandi : " + kos.getKamarMandi());
					System.out.println("Kasur : " + (kos.isKasur() ? "Ada" : "-"));
					System.out.println("Lemari : " + (kos.isLemari() ? "Ada" : "-"));
					System.out.println("Meja Kursi : " + (kos.isMejaKursi() ? "Ada" : "-"));
					System.out.println("AC : " + (kos.isAc() ? "Ada" : "-"));
					System.out.println("Air Panas : " + (kos.isAirPanas() ? "Ada" : "-"));
					System.out.println("Listrik : " + (kos.isListrik() ? "Termasuk" : "-"));
					System.out.println("Laundry : " + (kos.isLaundry() ? "Termasuk" : "-"));
					System.out.println("==========================================");
				}
			}
		}
	}
	
	private void addDummyData() {
		UserDatabase userDb = UserDatabase.getInstance();
		KosBuilder kb = new KosBuilder();
		
		kosDatabase.add(kb.reset().addUserId(userDb.getUserByIndex(0).getId()).addNama("Kos Anggrek Cakra").addJenis("Campur").addHarga(1500000).addAlamat("Jl. Anggrek Cakra No. 16D").addPanjangKamar(3).addLebarKamar(3).addKamarMandi("Luar").addKasur().addLemari().addMejaKursi().addAC().addLaundry().build());
		kosDatabase.add(kb.reset().addUserId(userDb.getUserByIndex(1).getId()).addNama("Kos U 99").addJenis("Putri").addHarga(1600000).addAlamat("Jl. U No. 99").addPanjangKamar(4).addLebarKamar(3).addKamarMandi("Dalam").addKasur().addLemari().addMejaKursi().addAC().addAirPanas().build());
	}

}
