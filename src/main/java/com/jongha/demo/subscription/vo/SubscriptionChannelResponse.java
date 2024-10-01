package com.jongha.demo.subscription.vo;

import com.jongha.demo.subscription.entity.SubscriptionEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Value;

@Value
public class SubscriptionChannelResponse {

    List<SubscriptionChannel> subscriptionChannels;

    public SubscriptionChannelResponse(List<SubscriptionEntity> entities) {
        this.subscriptionChannels = entities.stream()
            .map(SubscriptionChannel::new)
            .toList();
    }

    @Value
    public static class SubscriptionChannel {

        @Schema(description = "채널 ID", example = "1")
        Long channelId;
        @Schema(description = "채널 이름", example = "채널1")
        String channelName;
        @Schema(description = "구독 상태", example = "REGULAR")
        SubscriptionStatusEnum subscriptionStatus;
        @Schema(description = "첫 구독 일시", example = "2021-08-01T00:00:00")
        LocalDateTime firstSubscriptionAt;

        public SubscriptionChannel(SubscriptionEntity entity) {
            this.channelId = entity.getChannel().getId();
            this.channelName = entity.getChannel().getChannelName();
            this.subscriptionStatus = entity.getSubscriptionStatus();
            this.firstSubscriptionAt = entity.getCreatedAt();
        }
    }
}

