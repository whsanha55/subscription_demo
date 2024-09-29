package com.jongha.demo.subscription.vo;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnsubscriptionRequest {


    @NotNull
    private String phoneNumber;
    @NotNull
    private Long channelId;
    @NotNull
    private SubscriptionStatusEnum subscriptionStatus;

    public SubscriptionDTO toDTO(ChannelEntity channel) {
        return SubscriptionDTO.builder()
            .phoneNumber(phoneNumber)
            .channel(channel)
            .subscriptionStatus(subscriptionStatus)
            .build();
    }
}
