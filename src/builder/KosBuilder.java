package builder;

import model.Kos;

public class KosBuilder {
	private Kos kos;
	
	public KosBuilder reset() {
		kos = new Kos();
		
		return this;
	}
	
	public KosBuilder addUserId(String userId) {
		kos.setUserId(userId);
		
		return this;
	}
	
	public KosBuilder addNama(String nama) {
		kos.setNama(nama);
		
		return this;
	}
	
	public KosBuilder addJenis(String jenis) {
		kos.setJenis(jenis);
		
		return this;
	}
	
	public KosBuilder addHarga(int harga) {
		kos.setHarga(harga);
		
		return this;
	}
	
	public KosBuilder addAlamat(String alamat) {
		kos.setAlamat(alamat);
		
		return this;
	}
	
	public KosBuilder addPanjangKamar(int panjangKamar) {
		kos.setPanjangKamar(panjangKamar);
		
		return this;
	}
	
	public KosBuilder addLebarKamar(int lebarKamar) {
		kos.setLebarKamar(lebarKamar);
		
		return this;
	}
	
	public KosBuilder addKamarMandi(String kamarMandi) {
		kos.setKamarMandi(kamarMandi);
		
		return this;
	}
	
	public KosBuilder addKasur() {
		kos.setKasur(true);
		
		return this;
	}
	
	public KosBuilder addLemari() {
		kos.setLemari(true);
		
		return this;
	}
	
	public KosBuilder addMejaKursi() {
		kos.setMejaKursi(true);
		
		return this;
	}
	
	public KosBuilder addAC() {
		kos.setAc(true);
		
		return this;
	}
	
	public KosBuilder addAirPanas() {
		kos.setAirPanas(true);
		
		return this;
	}
	
	public KosBuilder addListrik() {
		kos.setListrik(true);
		
		return this;
	}
	
	public KosBuilder addLaundry() {
		kos.setLaundry(true);
		
		return this;
	}
	
	public Kos build() {
		return kos;
	}
}
