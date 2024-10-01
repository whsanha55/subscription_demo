package com.jongha.demo.channel.vo;

import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import com.jongha.demo.subscription.vo.SubscriptionHistoryResponse;
import java.util.List;
import lombok.Value;

@Value
public class ChannelSubscriptionHistoryResponse {

    List<SubscriptionHistoryResponse> histories;

    public ChannelSubscriptionHistoryResponse(List<SubscriptionHistoryEntity> entities) {
        this.histories = entities.stream()
            .map(SubscriptionHistoryResponse::new)
            .toList();
    }
}
