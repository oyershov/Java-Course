public class FlatForSale {
	public String address;
    public int floor;
    public double square;
    public int numOfRooms;
    public double price;
    
    @Override
    public String toString() {
        return "address: '" + address + '\'' +
                ", floor: '" + floor + '\'' +
                ", square: '" + square + '\'' +
                ", number of rooms: " + numOfRooms +
                ", price: " + price +
                " }";
    }
}