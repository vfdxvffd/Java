public class Exercise8 {
    public static void main(String[] args) {
        ParkedCar car = new ParkedCar("Volkswagen","1972","Red","147RHZM",125);
        PoliceOfficer officer = new PoliceOfficer("Joe Friday","4788");
        ParkingMeter meter = new ParkingMeter(60);
        ParkingTicket ticket = officer.patrol(car,meter);
        ticket.calculateFine();
        System.out.println(ticket.toString());
    }
}
