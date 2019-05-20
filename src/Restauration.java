public class Restauration {
    private String type;
    private String details;

    public Restauration(String type, String details) {
        this.type = type;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Restauration{" +
                "type='" + type + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
