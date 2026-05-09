package rentalapp.model;

public class User {
    private String idUser;
    private String nama;
    private String role; // Menyimpan nilai "admin" atau "kasir"
    private String password;

    public User() {}

    public User(String idUser, String nama, String role, String password) {
        this.idUser = idUser;
        this.nama = nama;
        this.role = role;
        this.password = password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        // Bisa ditambahkan validasi sederhana di sini jika diperlukan
        if(role.equals("admin") || role.equals("kasir")) {
            this.role = role;
        } else {
            throw new IllegalArgumentException("Role harus 'admin' atau 'kasir'");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}