package ium.progetto.iseeshop;

/**
 * Created by Utente on 12/05/2016.
 */
public class Prodotto {
    String nome;
    String prezzo;

    public Prodotto(String nome, String prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }
}
