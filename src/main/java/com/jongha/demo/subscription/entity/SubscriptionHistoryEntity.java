package com.jongha.demo.subscription.entity;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.global.base.BaseEntity;
import com.jongha.demo.global.converter.PhoneNumberConverter;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Entity(name = "subscription_history")
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriptionHistoryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = PhoneNumberConverter.class)
    private String phoneNumber;
    @ManyToOne(targetEntity = ChannelEntity.class)
    @JoinColumn(name = "channel_id")
    private ChannelEntity channel;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatusEnum oldSubscriptionStatus;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatusEnum subscriptionStatus;

    @Builder
    public SubscriptionHistoryEntity(SubscriptionEntity oldSubscriptionEntity, SubscriptionStatusEnum subscriptionStatus) {
        this.phoneNumber = oldSubscriptionEntity.getPhoneNumber();
        this.channel = oldSubscriptionEntity.getChannel();
        this.oldSubscriptionStatus = oldSubscriptionEntity.getSubscriptionStatus();
        this.subscriptionStatus = subscriptionStatus;

    }
}
