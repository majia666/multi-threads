package com.majia.fourthpart.chapter16;

public class FlightSecurityTest {
    // 旅客线程
    static class Passengers extends Thread{
        // 机场安检类
        private final FlightSecurity flightSecurity;

        // 旅客身份证
        private final String idCard;

        // 旅客登记牌
        private final String boardingPass;

        Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true){
                // 旅客不断的安检
                flightSecurity.pass(boardingPass,idCard);
            }
        }
    }
    public static void main(String[] args) {
        // 定义三个旅客，身份证和登机牌首字母均相同
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity,"A123456","AF123456").start();
        new Passengers(flightSecurity,"B123456","BF123456").start();
        new Passengers(flightSecurity,"C123456","CF123456").start();

    }
}
