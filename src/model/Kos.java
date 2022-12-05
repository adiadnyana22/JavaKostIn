package model;

import facade.IdGenerator;

public class Kos implements Cloneable {
	private String id;
	private String userId;
	private String nama;
	private String jenis;
	private int harga;
	private String alamat;
	private int panjangKamar;
	private int lebarKamar;
	private String kamarMandi;
	private boolean kasur;
	private boolean lemari;
	private boolean mejaKursi;
	private boolean ac;
	private boolean airPanas;
	private boolean listrik;
	private boolean laundry;
	
	public Kos() {
		this.id = IdGenerator.getKosId();
		
		this.kasur = false;
		this.lemari = false;
		this.mejaKursi = false;
		this.ac = false;
		this.airPanas = false;
		this.listrik = false;
		this.laundry = false;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public int getPanjangKamar() {
		return panjangKamar;
	}

	public void setPanjangKamar(int panjangKamar) {
		this.panjangKamar = panjangKamar;
	}

	public int getLebarKamar() {
		return lebarKamar;
	}

	public void setLebarKamar(int lebarKamar) {
		this.lebarKamar = lebarKamar;
	}

	public String getKamarMandi() {
		return kamarMandi;
	}

	public void setKamarMandi(String kamarMandi) {
		this.kamarMandi = kamarMandi;
	}

	public boolean isKasur() {
		return kasur;
	}

	public void setKasur(boolean kasur) {
		this.kasur = kasur;
	}

	public boolean isLemari() {
		return lemari;
	}

	public void setLemari(boolean lemari) {
		this.lemari = lemari;
	}

	public boolean isMejaKursi() {
		return mejaKursi;
	}

	public void setMejaKursi(boolean mejaKursi) {
		this.mejaKursi = mejaKursi;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public boolean isAirPanas() {
		return airPanas;
	}

	public void setAirPanas(boolean airPanas) {
		this.airPanas = airPanas;
	}

	public boolean isListrik() {
		return listrik;
	}

	public void setListrik(boolean listrik) {
		this.listrik = listrik;
	}

	public boolean isLaundry() {
		return laundry;
	}

	public void setLaundry(boolean laundry) {
		this.laundry = laundry;
	}
	
	@Override
	public Kos clone() throws CloneNotSupportedException {
		Object n = super.clone();
		Kos currObj = (Kos) n;
		currObj.id = IdGenerator.getKosId();
		
		return currObj;
	}
}
