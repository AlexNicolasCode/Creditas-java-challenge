public class Loan {
    private String type;

    public Loan(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public int tax() {
        switch (type) {
            case "GUARANTEE_LOAN":
                return 3;
            case "CONSIGNED_LOAN":
                return 2;
            default:
                return 4;
        }
    }
}
