public class FFS_Child extends FlatForSale implements Comparable<FFS_Child> {
    public static int ID;

    public FFS_Child() {
        ID++;
    }

    @Override
    public int compareTo(FFS_Child ffsChild) {
    	if(this.price<ffsChild.price)
            return -1;
    	else if(ffsChild.price<this.price)
    		return 1;
    	return 0;
    }
}

