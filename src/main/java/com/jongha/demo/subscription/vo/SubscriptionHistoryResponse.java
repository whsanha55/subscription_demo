package com.jongha.demo.subscription.vo;

import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionHistoryResponse {

    List<SubscriptionHistory> channelHistories;

    public SubscriptionHistoryResponse(List<SubscriptionHistoryEntity> entities) {
        this.channelHistories = entities.stream()
            .map(SubscriptionHistory::new)
            .toList();
    }

    @Value
    public static class SubscriptionHistory {

        Long channelId;
        String channelName;
        SubscriptionStatusEnum oldSubscriptionStatus;
        SubscriptionStatusEnum subscriptionStatus;
        LocalDateTime createdAt;

        public SubscriptionHistory(SubscriptionHistoryEntity entity) {
            this.channelId = entity.getChannel().getId();
            this.channelName = entity.getChannel().getChannelName();
            this.oldSubscriptionStatus = entity.getOldSubscriptionStatus();
            this.subscriptionStatus = entity.getSubscriptionStatus();
            this.createdAt = entity.getCreatedAt();
        }
    }
}
