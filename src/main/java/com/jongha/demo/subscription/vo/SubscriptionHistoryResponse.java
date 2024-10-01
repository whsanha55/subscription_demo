package com.jongha.demo.subscription.vo;

import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Value;

@Value
public class SubscriptionHistoryResponse {

    @Schema(description = "채널 ID", example = "1")
    Long channelId;
    @Schema(description = "채널 이름", example = "채널1")
    String channelName;
    @Schema(description = "이전 구독 상태", example = "REGULAR")
    SubscriptionStatusEnum oldSubscriptionStatus;
    @Schema(description = "현재 구독 상태", example = "PREMIUM")
    SubscriptionStatusEnum subscriptionStatus;
    @Schema(description = "이력 생성 일시", example = "2021-08-01T00:00:00")
    LocalDateTime createdAt;

    public SubscriptionHistoryResponse(SubscriptionHistoryEntity entity) {
        this.channelId = entity.getChannel().getId();
        this.channelName = entity.getChannel().getChannelName();
        this.oldSubscriptionStatus = entity.getOldSubscriptionStatus();
        this.subscriptionStatus = entity.getSubscriptionStatus();
        this.createdAt = entity.getCreatedAt();
    }
}

