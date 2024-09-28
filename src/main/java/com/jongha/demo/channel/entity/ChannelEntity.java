package com.jongha.demo.channel.entity;

import com.jongha.demo.global.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity(name = "channel")
@Builder
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChannelEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String channelName;
    private boolean ableSubscribe;
    private boolean ableUnsubscribe;
}
