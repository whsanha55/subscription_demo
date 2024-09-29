package com.jongha.demo.subscription.vo;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.subscription.entity.SubscriptionEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SubscriptionDTO {

    String phoneNumber;
    ChannelEntity channel;
    SubscriptionStatusEnum subscriptionStatus;

    public SubscriptionEntity toEntity() {
        return SubscriptionEntity.builder()
            .phoneNumber(phoneNumber)
            .channel(channel)
            .subscriptionStatus(subscriptionStatus)
            .subscriptionStatus(SubscriptionStatusEnum.NONE)
            .build();
    }
}
