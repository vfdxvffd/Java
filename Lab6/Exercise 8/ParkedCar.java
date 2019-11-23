public class ParkedCar{
         String make;
         String model;
         String color;
         String licenseNumber;
         int minutesParked;

         public ParkedCar(String mk,String mod,String col,String lic,int min){
             make = mk;
             model = mod;
             color = col;
             licenseNumber = lic;
             minutesParked = min;
         }

         public ParkedCar(ParkedCar car2){
             make = car2.make;
             model = car2.model;
             color = car2.color;
             licenseNumber = car2.licenseNumber;
             minutesParked = car2.minutesParked;
         }

         public String getMake() {
             return make;
         }

         public void setMake(String make) {
             this.make = make;
         }

         public String getModel() {
             return model;
         }

         public void setModel(String model) {
             this.model = model;
         }

         public String getColor() {
             return color;
         }

         public void setColor(String color) {
             this.color = color;
         }

         public String getLicenseNumber() {
             return licenseNumber;
         }

         public void setLicenseNumber(String licenseNumber) {
             this.licenseNumber = licenseNumber;
         }

         public int getMinutesParked() {
             return minutesParked;
         }

         public void setMinutesParked(int minutesParked) {
             this.minutesParked = minutesParked;
         }

         @Override
         public String toString() {
             return "Car Data:\n" +
                     "Make: "+make+"\n" +
                     "Model: "+model+"\n" +
                     "Color: "+color+"\n" +
                     "License Number: "+licenseNumber+"\n" +
                     "Minutes Parked: "+minutesParked+"\n";
    }
}