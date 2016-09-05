package mei.meiweatherapp.contratos;

/**
 * Created by Hugo on 31-Aug-16.
 */
    public class Praia {
    private int id;
    private String nome;
    private String longitude;
    private String latitude;
    private String morada;
    private int favorita;

    //CONSTRUTORES
    public Praia() {
    }

    public Praia(int favorita, int id, String latitude, String longitude, String nome, String morada) {
        this.favorita = favorita;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
        this.morada = morada;

    }

    //GET & SET
    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getFavorita() {
        return favorita;
    }

    public void setFavorita(int favorita) {
        this.favorita = favorita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "" + nome +
                ", " + longitude +
                ", " + latitude;
    }

}