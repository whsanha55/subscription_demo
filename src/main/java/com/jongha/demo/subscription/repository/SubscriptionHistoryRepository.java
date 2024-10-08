package com.jongha.demo.subscription.repository;

import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistoryEntity, Long>,
    JpaSpecificationExecutor<SubscriptionHistoryEntity> {

}
