package org.example;

public class App {
    public static void main( String[] args ) {
        WriteNumber writeNumber = new WriteNumber(1,1000);

        Thread thread = new Thread(writeNumber::writeEvenNumber);
        thread.setName("Even Thread");
        Thread thread2 = new Thread(writeNumber::writeOddNumber);
        thread2.setName("Odd Thread");

        thread.start();
        thread2.start();
    }
}
