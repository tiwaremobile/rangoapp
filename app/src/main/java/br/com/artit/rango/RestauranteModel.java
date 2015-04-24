package br.com.artit.rango;

public class RestauranteModel {

    String nome;
    String endereco;
    String distancia;
    String latitude;
    String longitude;

    public RestauranteModel(String nome, String endereco, String distancia, String latitude, String longitude) {
        this.nome = nome;
        this.endereco = endereco;
        this.distancia = distancia;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
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

}
