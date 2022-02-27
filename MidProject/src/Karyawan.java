
public class Karyawan {

	public String nama;
	public String jenisKelamin;
	public String jabatan;
	public Double gaji;
	public String idKaryawan;

	public Karyawan(String nama, String jenisKelamin, String jabatan, Double gaji, String idKaryawan) {
		super();
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.jabatan = jabatan;
		this.gaji = gaji;
		this.idKaryawan = idKaryawan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public Double getGaji() {
		return gaji;
	}

	public void setGaji(Double gaji) {
		this.gaji = gaji;
	}

	public String getIdKaryawan() {
		return idKaryawan;
	}

	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}
	
	

}
