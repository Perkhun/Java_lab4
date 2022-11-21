package lpnu.entity.enumeration;

public enum Achievement {
    STRANGER("Stranger"),
    BEGINNER("Beginner"),
    MIDDLE("Middle"),
    EXPERT("Expert"),
    KING("King");

    private final String text;

    Achievement(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
