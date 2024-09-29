package com.jongha.demo.subscription.vo;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UnsubscriptionDTO {

    String phoneNumber;
    Long channelId;
    ChannelEntity channel;
    SubscriptionStatusEnum subscriptionStatus;

}
