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

    public UnsubscriptionDTO toDTO(ChannelEntity channel) {
        return UnsubscriptionDTO.builder()
            .phoneNumber(phoneNumber)
            .channel(channel)
            .subscriptionStatus(subscriptionStatus)
            .build();
    }
}
