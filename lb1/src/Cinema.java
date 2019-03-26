public class Cinema {
	public String name;
    public String filmTitle;
    public double filmStartTime;
    public double ticketCost;
    public int viewersCount;
    
    @Override
    public String toString() {
        return "Name of the cinema: '" + name + '\'' +
                ", film title: '" + filmTitle + '\'' +
                ", film start time : '" + filmStartTime + '\'' +
                ", ticket cost: " + ticketCost +
                ", count of viewers: " + viewersCount +
                " }";
    }
}