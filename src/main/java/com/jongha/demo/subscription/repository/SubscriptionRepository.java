package com.jongha.demo.subscription.repository;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.subscription.entity.SubscriptionEntity;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<SubscriptionEntity> findAndLockByPhoneNumberAndChannel(String phoneNumber, ChannelEntity channel);

    @EntityGraph(attributePaths = "channel")
    List<SubscriptionEntity> findByPhoneNumber(String phoneNumber);
}
