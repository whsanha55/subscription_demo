package com.jongha.demo.subscription.enums;

import com.jongha.demo.exception.BaseException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubscriptionStatusEnum {
    NONE(0),
    REGULAR(10),
    PREMIUM(20),
    ;

    private final int grade;

    public void validateSubscribe(SubscriptionStatusEnum newStatus, Purpose purpose) {
        var violated = switch (purpose) {
            case SUBSCRIBE -> this.grade <= newStatus.grade;
            case UNSUBSCRIBE -> this.grade >= newStatus.grade;
        };

        if (violated) {
            throw new BaseException("Invalid subscription status");
        }
    }

    public enum Purpose {
        SUBSCRIBE,
        UNSUBSCRIBE
    }
}
