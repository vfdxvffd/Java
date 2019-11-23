public class ParkingTicket{

        final static double BASH_FINE = 25.0;
        final static double HOURLY_FINE = 10.0;

        ParkedCar car;
        PoliceOfficer officer;
        double fine;
        int minutes;                    //合法时间

        public ParkingTicket(ParkedCar car, PoliceOfficer officer) {
            this.car = car;
            this.officer = officer;
        }

        public ParkingTicket(ParkingTicket ticket2) {
            this.car = ticket2.car;
            this.officer = ticket2.officer;
            this.minutes = ticket2.minutes;
        }

        public ParkedCar getCar() {
            return car;
        }

        public void setCar(ParkedCar car) {
            this.car = car;
        }

        public PoliceOfficer getOfficer() {
            return officer;
        }

        public void setOfficer(PoliceOfficer officer) {
            this.officer = officer;
        }

        public double getFine() {
            return fine;
        }

        public void setFine(double fine) {
            this.fine = fine;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public void calculateFine(){
            int illegal = car.minutesParked - this.minutes;
            int hour = 0;
            if(illegal % 60 != 0){
                hour = illegal/60+1;
            }
            this.fine = BASH_FINE + hour*HOURLY_FINE;
        }

        @Override
        public String toString() {
            return car.toString()+"\n"+officer.toString()+"\n" +
                    "Minutes Illegally Parked: "+(car.minutesParked-minutes)+"\n" +
                    "Fine: $"+String.format("%.2f",fine);
        }
    }