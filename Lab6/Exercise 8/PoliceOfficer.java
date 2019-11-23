public class PoliceOfficer{
         String name;
         String badgeNumber;

         public PoliceOfficer(String name, String badgeNumber) {
             this.name = name;
             this.badgeNumber = badgeNumber;
         }

         public PoliceOfficer (PoliceOfficer officer2) {
            this.name = officer2.name;
            this.badgeNumber = officer2.badgeNumber;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getBadgeNumber() {
             return badgeNumber;
         }

         public void setBadgeNumber(String badgeNumber) {
             this.badgeNumber = badgeNumber;
         }

         public ParkingTicket patrol(ParkedCar car, ParkingMeter meter){
             ParkingTicket ticket = new ParkingTicket(car,this);
             ticket.car = car;
             ticket.officer = this;
             ticket.minutes = meter.minutesPurchased;
             return ticket;
         }

         @Override
         public String toString() {
             return "Officer Data:\n" +
                     "Name: "+name+"\n" +
                     "BadgeNumber: "+badgeNumber+"\n";
         }
     }