package nl.vue.blocker.vueblocker.movies.domain;

public enum Location {

    EINDHOVEN(23);

    private final int id;

    Location(final int newValue) {
        id = newValue;
    }

    public int getId() { return id; }
}
