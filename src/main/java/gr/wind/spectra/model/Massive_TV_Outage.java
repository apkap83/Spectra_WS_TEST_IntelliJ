package gr.wind.spectra.model;

public enum Massive_TV_Outage {
    Massive_TV_Outage("Massive_TV_Outage"),
    ALL_EON_Boxes("ALL_EON_Boxes"),
    ALL_Satellite_Boxes("ALL_Satellite_Boxes");
    private final String displayName;

    Massive_TV_Outage(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
