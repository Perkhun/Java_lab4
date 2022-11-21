package lpnu.entity.enumeration;

public enum Status {
    ISSUANCE_OF_BOOK("issuance of book"),
    BOOK_RETURN("book return");

    private final String text;

    Status(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
