package org.example;

public class WriteNumber {

    private final int limit;
    private volatile int startNumber;
    private final Object lock = new Object();

    public WriteNumber(int startNumber, int limit) {
        this.startNumber = startNumber;
        this.limit = limit;
    }

    public void writeEvenNumber() {
        synchronized (lock) {
            while (startNumber < limit) {
                if (startNumber % 2 == 0) {
                    System.out.println(startNumber + " Wrote " + Thread.currentThread().getName());
                    startNumber++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public void writeOddNumber() {
        synchronized (lock) {
            while (startNumber < limit) {
                if (startNumber % 2 != 0) {
                    System.out.println(startNumber + " Wrote " + Thread.currentThread().getName());
                    startNumber++;
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}