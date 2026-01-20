package edu.icet.ecom.Service;

import lombok.Getter;

@Getter
public class SeatLockedException extends RuntimeException {
    private final long remainingSeconds;
    public SeatLockedException(long remainingSeconds) {
        super("Seat is currently held by another user.");
        this.remainingSeconds = remainingSeconds;
    }
}
