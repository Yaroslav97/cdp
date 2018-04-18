package cdp.entity;

public enum Goods {
    LIQUIDS("liquids"),
    COUNTABLE("countable");

    private String name;

    Goods(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
