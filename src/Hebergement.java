public class Hebergement {
    private int numero;
    private String type;
    private String localisation;
    private int nb_max;
    private int prix_nuit;

    public Hebergement(int numero, String type, String localisation, int nb_max, int prix_nuit) {
        this.numero = numero;
        this.type = type;
        this.localisation = localisation;
        this.nb_max = nb_max;
        this.prix_nuit = prix_nuit;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    public int getPrix_nuit() {
        return prix_nuit;
    }

    public void setPrix_nuit(int prix_nuit) {
        this.prix_nuit = prix_nuit;
    }

    @Override
    public String toString() {
        return "Hebergement{" +
                "numero=" + numero +
                ", type='" + type + '\'' +
                ", localisation='" + localisation + '\'' +
                ", nb_max=" + nb_max +
                ", prix_nuit=" + prix_nuit +
                '}';
    }
}
