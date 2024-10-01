package com.jongha.demo.subscription.repository;

import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistoryEntity, Long>,
    JpaSpecificationExecutor<SubscriptionHistoryEntity> {

    List<SubscriptionHistoryEntity> findByPhoneNumber(String phoneNumber);

    //    @EntityGraph(attributePaths = "channel", type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = """
            select sh.*
            from subscription_history sh
            join channel c on sh.channel_id = c.id
            where sh.created_at >= :date
            and sh.created_at < :date + interval 1 day
            and sh.channel_id = :channelId
        """, nativeQuery = true)
    List<SubscriptionHistoryEntity> findByDateAndChannel(LocalDate date, Long channelId);
}
