package Content;


public enum ScoreSource {
    Animal("Animal score"),
    Activity("Activity score"),
    Collections("Collections score"),
    ThrowCatch("Throw Catch score"),
    Region("Region score"),
    Sites("Visited sites score");

    private final String abbreviation;
    ScoreSource(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public String getAbbreviation() {
        return this.abbreviation;
    }
}