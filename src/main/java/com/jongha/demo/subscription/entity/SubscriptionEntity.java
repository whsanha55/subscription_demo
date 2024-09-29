package com.jongha.demo.subscription.entity;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.global.base.BaseEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum.Purpose;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity(name = "subscription")
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatusEnum subscriptionStatus;

    @ManyToOne(targetEntity = ChannelEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private ChannelEntity channel;

    public void updateSubscriptionStatus(SubscriptionStatusEnum subscriptionStatus, Purpose purpose) {
        // 구독 상태 변경 가능 여부 검증
        this.subscriptionStatus.validateSubscribe(subscriptionStatus, purpose);
        this.subscriptionStatus = subscriptionStatus;
    }

}
