import java.time.LocalDate;

public class Reservation {
    private Client client;
    private Hebergement hebergement;
    LocalDate date_debut;
    LocalDate date_fin;

    public Reservation(Client client, Hebergement hebergement, LocalDate date_debut, LocalDate date_fin) {
        this.client = client;
        this.hebergement = hebergement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "client=" + client +
                ", hebergement=" + hebergement +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                '}';
    }
}
