public class Cinema_Child extends Cinema implements Comparable<Cinema_Child> {
    public static int ID;

    public Cinema_Child() {
        ID++;
    }

    @Override
    public int compareTo(Cinema_Child cinemaChild) {
    	if(this.viewersCount<cinemaChild.viewersCount)
            return -1;
    	else if(cinemaChild.viewersCount<this.viewersCount)
    		return 1;
    	return 0;
    }
}

