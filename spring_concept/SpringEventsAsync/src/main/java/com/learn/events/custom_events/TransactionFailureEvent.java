package com.learn.events.custom_events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransactionFailureEvent {
    private String userName;
    private double amount;
}
