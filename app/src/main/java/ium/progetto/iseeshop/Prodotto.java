package ium.progetto.iseeshop;

/**
 * Created by Alina on 12/05/2016.
 */
public class Prodotto {
    private String nome;
    private float prezzo;
    private String produttore;
    private String scadenza;
    private String dataProduzione;
    private int quantita;
    private int idImmagine;

    public Prodotto(String nome, float prezzo, String produttore, String scadenza, String dataProduzione, int quantita, int idImmagine) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.produttore = produttore;
        this.scadenza = scadenza;
        this.dataProduzione = dataProduzione;
        this.quantita=quantita;
        this.idImmagine=idImmagine;
    }

    public int getIdImmagine() {
        return idImmagine;
    }

    public void setIdImmagine(int idImmagine) {
        this.idImmagine = idImmagine;
    }

    public String getProduttore() {
        return produttore;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getDataProduzione() {
        return dataProduzione;
    }

    public void setDataProduzione(String dataProduzione) {
        this.dataProduzione = dataProduzione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
