public class Study {
    private int week;
    private int season;

    public Study(int week, int season) {
        this.week = week;
        this.season = season;
    }

    @Override
    public int hashCode() {
        return week;
    }

    @Override
    public boolean equals(Object object) {
        Study other = (Study) object;
        return this.week == other.week && this.season == other.season;
    }

    public boolean compareSeason(int otherSeason) {
        return this.season == otherSeason;
    }
}
