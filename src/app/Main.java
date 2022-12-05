package app;

import java.util.Scanner;

import builder.KosBuilder;
import database.KosDatabase;
import database.OrderDatabase;
import database.UserDatabase;
import factory.UserFactory;
import model.Kos;
import model.Order;
import model.PemilikKos;
import model.PencariKos;
import model.User;

public class Main {
	private Scanner sc = new Scanner(System.in);

	public Main() {
		UserDatabase userDb = UserDatabase.getInstance();
		KosDatabase kosDb = KosDatabase.getInstance();
		OrderDatabase orderDb = OrderDatabase.getInstance();
		int pilihLoginMenu;
		
		while(true) {
			do {
				System.out.println("Selamat Datang di KostIn");
				System.out.println("========================");
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Exit");
				System.out.print(">> ");
				pilihLoginMenu = sc.nextInt(); sc.nextLine();
			} while(pilihLoginMenu != 1 && pilihLoginMenu != 2 && pilihLoginMenu != 3);
			
			if(pilihLoginMenu == 1) {
				boolean login = false;
				String username, password;
				User user = null;
				
				while(!login) {
					System.out.print("Masukkan Username : ");
					username = sc.nextLine();
					System.out.print("Masukkan Password : ");
					password = sc.nextLine();
					
					user = userDb.login(username, password);
					if(user == null) System.out.println("\nUsername atau Password Salah\n");
					else login = true;
				}
				
				if(user instanceof PemilikKos) {
					int pilihPemilikKosMenu;
					
					while(true) {
						do {
							System.out.println("Menu Pemilik Kos :");
							System.out.println("1. Tampilkan Kos Saya");
							System.out.println("2. Tambah Kos Baru");
							System.out.println("3. Tambah Kos Serupa");
							System.out.println("4. Edit Kos");
							System.out.println("5. Hapus Kos");
							System.out.println("6. Kembali");
							System.out.print(">> ");
							pilihPemilikKosMenu = sc.nextInt(); sc.nextLine();
						} while(pilihPemilikKosMenu < 1 || pilihPemilikKosMenu > 6);
						
						if(pilihPemilikKosMenu == 1) {
							kosDb.printUserKos(user.getId());
						} else if(pilihPemilikKosMenu == 2) {
							String nama, jenis, alamat, kamarMandi;
							int harga, panjangKamar, lebarKamar;
							String kasur, lemari, mejaKursi, ac, airPanas, listrik, laundry;
							KosBuilder kosBuilder = new KosBuilder();
							
							System.out.print("Masukkan Nama Kos : ");
							nama = sc.nextLine();
							do {
								System.out.print("Masukkan Jenis Kos [ Putra | Putri | Campur ] : ");
								jenis = sc.nextLine();
							} while(!jenis.equals("Putra") && !jenis.equals("Putri") && !jenis.equals("Campur"));
							System.out.print("Masukkan Alamat Kos : ");
							alamat = sc.nextLine();
							System.out.print("Masukkan Harga Kos : ");
							harga = sc.nextInt(); sc.nextLine();
							System.out.print("Masukkan Panjang Kamar Kos (dalam Meter) : ");
							panjangKamar = sc.nextInt(); sc.nextLine();
							System.out.print("Masukkan Lebar Kamar Kos (dalam Meter) : ");
							lebarKamar = sc.nextInt(); sc.nextLine();
							do {
								System.out.print("Masukkan Tipe Kamar Mandi [ Dalam | Luar ] : ");
								kamarMandi = sc.nextLine();
							} while(!kamarMandi.equals("Dalam") && !kamarMandi.equals("Luar"));
							
							kosBuilder = kosBuilder.reset().addUserId(user.getId()).addNama(nama).addJenis(jenis).addAlamat(alamat).addHarga(harga).addPanjangKamar(panjangKamar).addLebarKamar(lebarKamar).addKamarMandi(kamarMandi);
							
							System.out.println("Fasilitas : (Jika tersedia tulis 'Y' dan jika tidak kosongkan saja)");
							do {
								System.out.print("Apakah Kasur tersedia ? ");
								kasur = sc.nextLine();
								
								if(kasur.equals("Y")) {
									kosBuilder = kosBuilder.addKasur();
									break;
								}
								else if(kasur.trim().equals("")) break;
							} while(true);
							do {
								System.out.print("Apakah Lemari tersedia ? ");
								lemari = sc.nextLine();
								
								if(lemari.equals("Y")) {
									kosBuilder = kosBuilder.addLemari();
									break;
								}
								else if(lemari.trim().equals("")) break;
							} while(true);
							do {
								System.out.print("Apakah Meja & Kursi tersedia ? ");
								mejaKursi = sc.nextLine();
								
								if(mejaKursi.equals("Y")) {
									kosBuilder = kosBuilder.addMejaKursi();
									break;
								}
								else if(mejaKursi.trim().equals("")) break;
							} while(true);
							do {
								System.out.print("Apakah AC tersedia ? ");
								ac = sc.nextLine();
								
								if(ac.equals("Y")) {
									kosBuilder = kosBuilder.addAC();
									break;
								}
								else if(ac.trim().equals("")) break;
							} while(true);
							do {
								System.out.print("Apakah Air Panas tersedia ? ");
								airPanas = sc.nextLine();
								
								if(airPanas.equals("Y")) {
									kosBuilder = kosBuilder.addAirPanas();
									break;
								}
								else if(airPanas.trim().equals("")) break;
							} while(true);
							do {
								System.out.print("Apakah Listrik termasuk termasuk dalam harga ? ");
								listrik = sc.nextLine();
								
								if(listrik.equals("Y")) {
									kosBuilder = kosBuilder.addListrik();
									break;
								}
								else if(listrik.trim().equals("")) break;
							} while(true);
							do {
								System.out.print("Apakah Laundry termasuk termasuk dalam harga ? ");
								laundry = sc.nextLine();
								
								if(laundry.equals("Y")) {
									kosBuilder = kosBuilder.addLaundry();
									break;
								}
								else if(laundry.trim().equals("")) break;
							} while(true);
							
							kosDb.addKos(kosBuilder.build());
							System.out.println("\nKos Berhasil Ditambahkan!\n");
						} else if(pilihPemilikKosMenu == 3) {
							String kosId;
							
							kosDb.printUserKos(user.getId());
							System.out.print("Masukkan Kos Id yang ingin diduplikat : ");
							kosId = sc.nextLine();
							
							Kos kos = kosDb.getKosById(kosId);
							if(kos == null) System.out.println("\nKos Id Tidak Ditemukan\n");
							else {
								int pilihUbahData;
								Kos duplicateKos = null;
								
								try {
									duplicateKos = kos.clone();
								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								while(true) {
									do {
										System.out.println("Pilih Bagian Yang Berubah :");
										System.out.println("1. Jenis Kos");
										System.out.println("2. Harga Kos");
										System.out.println("3. Ukuran Kos");
										System.out.println("4. Tipe Kamar Mandi");
										System.out.println("5. Ketersediaan Kasur");
										System.out.println("6. Ketersediaan Lemari");
										System.out.println("7. Ketersediaan Meja & Kursi");
										System.out.println("8. Ketersediaan AC");
										System.out.println("9. Ketersediaan Air Panas");
										System.out.println("10. Ketersediaan Listrik");
										System.out.println("11. Ketersediaan Laundry");
										System.out.println("12. Selesai");
										System.out.print(">> ");
										pilihUbahData = sc.nextInt(); sc.nextLine();
									} while(pilihUbahData < 1 || pilihUbahData > 12);
									
									if(pilihUbahData == 1) {
										String jenis;
										
										do {
											System.out.print("Masukkan Jenis Kos [ Putra | Putri | Campur ] : ");
											jenis = sc.nextLine();
										} while(!jenis.equals("Putra") && !jenis.equals("Putri") && !jenis.equals("Campur"));
										duplicateKos.setJenis(jenis);
									} else if(pilihUbahData == 2) {
										int harga;
										
										System.out.print("Masukkan Harga Kos : ");
										harga = sc.nextInt(); sc.nextLine();
										duplicateKos.setHarga(harga);
									} else if(pilihUbahData == 3) {
										int panjangKamar, lebarKamar;
										
										System.out.print("Masukkan Panjang Kamar Kos (dalam Meter) : ");
										panjangKamar = sc.nextInt(); sc.nextLine();
										System.out.print("Masukkan Lebar Kamar Kos (dalam Meter) : ");
										lebarKamar = sc.nextInt(); sc.nextLine();
										duplicateKos.setPanjangKamar(panjangKamar);
										duplicateKos.setLebarKamar(lebarKamar);
									} else if(pilihUbahData == 4) {
										String kamarMandi;
										
										do {
											System.out.print("Masukkan Tipe Kamar Mandi [ Dalam | Luar ] : ");
											kamarMandi = sc.nextLine();
										} while(!kamarMandi.equals("Dalam") && !kamarMandi.equals("Luar"));
										duplicateKos.setKamarMandi(kamarMandi);
									} else if(pilihUbahData == 5) {
										String kasur;
										
										do {
											System.out.print("Apakah Kasur tersedia [ Ya | Tidak ] ? ");
											kasur = sc.nextLine();
											
											if(kasur.equals("Ya")) {
												duplicateKos.setKasur(true);
												break;
											}
											else if(kasur.equals("Tidak")) {
												duplicateKos.setKasur(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 6) {
										String lemari;
										
										do {
											System.out.print("Apakah Lemari tersedia [ Ya | Tidak ] ? ");
											lemari = sc.nextLine();
											
											if(lemari.equals("Ya")) {
												duplicateKos.setLemari(true);
												break;
											}
											else if(lemari.equals("Tidak")) {
												duplicateKos.setLemari(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 7) {
										String mejaKursi;
										
										do {
											System.out.print("Apakah Meja & Kursi tersedia [ Ya | Tidak ] ? ");
											mejaKursi = sc.nextLine();
											
											if(mejaKursi.equals("Ya")) {
												duplicateKos.setMejaKursi(true);
												break;
											}
											else if(mejaKursi.equals("Tidak")) {
												duplicateKos.setMejaKursi(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 8) {
										String ac;
										
										do {
											System.out.print("Apakah AC tersedia [ Ya | Tidak ] ? ");
											ac = sc.nextLine();
											
											if(ac.equals("Ya")) {
												duplicateKos.setAc(true);
												break;
											}
											else if(ac.equals("Tidak")) {
												duplicateKos.setAc(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 9) {
										String airPanas;
										
										do {
											System.out.print("Apakah Air Panas tersedia [ Ya | Tidak ] ? ");
											airPanas = sc.nextLine();
											
											if(airPanas.equals("Ya")) {
												duplicateKos.setAirPanas(true);
												break;
											}
											else if(airPanas.equals("Tidak")) {
												duplicateKos.setAirPanas(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 10) {
										String listrik;
										
										do {
											System.out.print("Apakah Listrik termasuk termasuk dalam harga [ Ya | Tidak ] ? ");
											listrik = sc.nextLine();
											
											if(listrik.equals("Ya")) {
												duplicateKos.setListrik(true);
												break;
											}
											else if(listrik.equals("Tidak")) {
												duplicateKos.setListrik(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 11) {
										String laundry;
										
										do {
											System.out.print("Apakah Laundry termasuk termasuk dalam harga [ Ya | Tidak ] ? ");
											laundry = sc.nextLine();
											
											if(laundry.equals("Ya")) {
												duplicateKos.setLaundry(true);
												break;
											}
											else if(laundry.equals("Tidak")) {
												duplicateKos.setLaundry(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 12) {
										kosDb.addKos(duplicateKos);
										System.out.println("\nKos Berhasil Ditambahkan\n");
										break;
									}
								}
							}
						} else if(pilihPemilikKosMenu == 4) {
							String kosId;
							
							kosDb.printUserKos(user.getId());
							System.out.print("Masukkan Kos Id yang ingin diedit : ");
							kosId = sc.nextLine();
							
							Kos kos = kosDb.getKosById(kosId);
							if(kos == null) System.out.println("\nKos Id Tidak Ditemukan\n");
							else {
								int pilihUbahData;
								
								while(true) {
									do {
										System.out.println("Pilih Bagian Yang Berubah :");
										System.out.println("1. Nama Kos");
										System.out.println("2. Jenis Kos");
										System.out.println("3. Alamat Kos");
										System.out.println("4. Harga Kos");
										System.out.println("5. Ukuran Kos");
										System.out.println("6. Tipe Kamar Mandi");
										System.out.println("7. Ketersediaan Kasur");
										System.out.println("8. Ketersediaan Lemari");
										System.out.println("9. Ketersediaan Meja & Kursi");
										System.out.println("10. Ketersediaan AC");
										System.out.println("11. Ketersediaan Air Panas");
										System.out.println("12. Ketersediaan Listrik");
										System.out.println("13. Ketersediaan Laundry");
										System.out.println("14. Selesai");
										System.out.print(">> ");
										pilihUbahData = sc.nextInt(); sc.nextLine();
									} while(pilihUbahData < 1 || pilihUbahData > 14);
									
									if(pilihUbahData == 1) {
										String nama;
										
										System.out.print("Masukkan Nama Kos : ");
										nama = sc.nextLine();
										kos.setNama(nama);
									} else if(pilihUbahData == 2) {
										String jenis;
										
										do {
											System.out.print("Masukkan Jenis Kos [ Putra | Putri | Campur ] : ");
											jenis = sc.nextLine();
										} while(!jenis.equals("Putra") && !jenis.equals("Putri") && !jenis.equals("Campur"));
										kos.setJenis(jenis);
									} else if(pilihUbahData == 3) {
										String alamat;
										
										System.out.print("Masukkan Alamat Kos : ");
										alamat = sc.nextLine();
										kos.setNama(alamat);
									} else if(pilihUbahData == 4) {
										int harga;
										
										System.out.print("Masukkan Harga Kos : ");
										harga = sc.nextInt(); sc.nextLine();
										kos.setHarga(harga);
									} else if(pilihUbahData == 5) {
										int panjangKamar, lebarKamar;
										
										System.out.print("Masukkan Panjang Kamar Kos (dalam Meter) : ");
										panjangKamar = sc.nextInt(); sc.nextLine();
										System.out.print("Masukkan Lebar Kamar Kos (dalam Meter) : ");
										lebarKamar = sc.nextInt(); sc.nextLine();
										kos.setPanjangKamar(panjangKamar);
										kos.setLebarKamar(lebarKamar);
									} else if(pilihUbahData == 6) {
										String kamarMandi;
										
										do {
											System.out.print("Masukkan Tipe Kamar Mandi [ Dalam | Luar ] : ");
											kamarMandi = sc.nextLine();
										} while(!kamarMandi.equals("Dalam") && !kamarMandi.equals("Luar"));
										kos.setKamarMandi(kamarMandi);
									} else if(pilihUbahData == 7) {
										String kasur;
										
										do {
											System.out.print("Apakah Kasur tersedia [ Ya | Tidak ] ? ");
											kasur = sc.nextLine();
											
											if(kasur.equals("Ya")) {
												kos.setKasur(true);
												break;
											}
											else if(kasur.equals("Tidak")) {
												kos.setKasur(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 8) {
										String lemari;
										
										do {
											System.out.print("Apakah Lemari tersedia [ Ya | Tidak ] ? ");
											lemari = sc.nextLine();
											
											if(lemari.equals("Ya")) {
												kos.setLemari(true);
												break;
											}
											else if(lemari.equals("Tidak")) {
												kos.setLemari(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 9) {
										String mejaKursi;
										
										do {
											System.out.print("Apakah Meja & Kursi tersedia [ Ya | Tidak ] ? ");
											mejaKursi = sc.nextLine();
											
											if(mejaKursi.equals("Ya")) {
												kos.setMejaKursi(true);
												break;
											}
											else if(mejaKursi.equals("Tidak")) {
												kos.setMejaKursi(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 10) {
										String ac;
										
										do {
											System.out.print("Apakah AC tersedia [ Ya | Tidak ] ? ");
											ac = sc.nextLine();
											
											if(ac.equals("Ya")) {
												kos.setAc(true);
												break;
											}
											else if(ac.equals("Tidak")) {
												kos.setAc(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 11) {
										String airPanas;
										
										do {
											System.out.print("Apakah Air Panas tersedia [ Ya | Tidak ] ? ");
											airPanas = sc.nextLine();
											
											if(airPanas.equals("Ya")) {
												kos.setAirPanas(true);
												break;
											}
											else if(airPanas.equals("Tidak")) {
												kos.setAirPanas(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 12) {
										String listrik;
										
										do {
											System.out.print("Apakah Listrik termasuk termasuk dalam harga [ Ya | Tidak ] ? ");
											listrik = sc.nextLine();
											
											if(listrik.equals("Ya")) {
												kos.setListrik(true);
												break;
											}
											else if(listrik.equals("Tidak")) {
												kos.setListrik(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 13) {
										String laundry;
										
										do {
											System.out.print("Apakah Laundry termasuk termasuk dalam harga [ Ya | Tidak ] ? ");
											laundry = sc.nextLine();
											
											if(laundry.equals("Ya")) {
												kos.setLaundry(true);
												break;
											}
											else if(laundry.equals("Tidak")) {
												kos.setLaundry(false);
												break;
											}
										} while(true);
									} else if(pilihUbahData == 14) {
										System.out.println("\nKos Berhasil Diedit\n");
										break;
									}
								}
							}
						} else if(pilihPemilikKosMenu == 5) {
							String kosId;
							
							kosDb.printUserKos(user.getId());
							System.out.print("Masukkan Kos Id yang ingin dihapus : ");
							kosId = sc.nextLine();
							if(kosDb.deleteKosById(kosId)) System.out.println("\nKos Berhasil Dihapus\n");
							else System.out.println("\nKos Id Tidak Ditemukan\n");
						} else if(pilihPemilikKosMenu == 6) {
							break;
						}
					}
				} else if(user instanceof PencariKos) {
					int pilihPencariKosMenu;
					
					while(true) {
						do {
							System.out.println("Menu Pencari Kos :");
							System.out.println("1. Tampilkan Kos Yang Tersedia");
							System.out.println("2. Booking Kos");
							System.out.println("3. Tampilkan History Booking");
							System.out.println("4. Kembali");
							System.out.print(">> ");
							pilihPencariKosMenu = sc.nextInt(); sc.nextLine();
						} while(pilihPencariKosMenu < 1 || pilihPencariKosMenu > 4);
						
						if(pilihPencariKosMenu == 1) {
							System.out.println("Kos Yang Tersedia :");
							kosDb.printAllKos();
						} else if(pilihPencariKosMenu == 2) {
							String kosId;
							int jangkaWaktu;
							Kos kos;
							
							System.out.println("Kos Yang Tersedia :");
							kosDb.printAllKos();
							System.out.print("Masukkan Kos Id yang ingin dibooking : ");
							kosId = sc.nextLine();
							kos = kosDb.getKosById(kosId);
							
							if(kos == null) System.out.println("\nKos Id Tidak Ditemukan\n");
							else {
								System.out.print("Masukkan Jangka Waktu Booking (dalam Bulan) : ");
								jangkaWaktu = sc.nextInt(); sc.nextLine();
								
								orderDb.addOrder(new Order(user.getId(), kosId, jangkaWaktu));
								System.out.println("\nOrder Berhasil Ditambahkan\n");
							}
						} else if(pilihPencariKosMenu == 3) {
							System.out.println("Order History :");
							orderDb.printOrderByUserId(user.getId());
						} else if(pilihPencariKosMenu == 4) {
							break;
						}
					}
					
				}
			} else if(pilihLoginMenu == 2) {
				String username, password, role;
				UserFactory userFactory = new UserFactory();
				
				System.out.print("Masukkan Username : ");
				username = sc.nextLine();
				System.out.print("Masukkan Password : ");
				password = sc.nextLine();
				
				do {
					System.out.print("Pilih Role [Pemilik Kos | Pencari Kos] : ");
					role = sc.nextLine();
				} while(!role.equals("Pemilik Kos") && !role.equals("Pencari Kos"));
				
				if(role.equals("Pemilik Kos")) userDb.addUser(userFactory.getPemilikKos(username, password));
				else if(role.equals("Pencari Kos")) userDb.addUser(userFactory.getPencariKos(username, password));
			} else if(pilihLoginMenu == 3) {
				System.out.println("Terima Kasih!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
