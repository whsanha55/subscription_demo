package com.jongha.demo.subscription.service;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.exception.BaseException;
import com.jongha.demo.subscription.entity.SubscriptionEntity;
import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import com.jongha.demo.subscription.enums.SubscriptionStatusEnum.Purpose;
import com.jongha.demo.subscription.repository.SubscriptionHistoryRepository;
import com.jongha.demo.subscription.repository.SubscriptionRepository;
import com.jongha.demo.subscription.vo.SubscriptionDTO;
import com.jongha.demo.subscription.vo.UnsubscriptionDTO;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionHistoryRepository subscriptionHistoryRepository;


    @Transactional
    public void subscribe(SubscriptionDTO dto) {

        var entity = getAndLockSubscriptionOptional(dto.getPhoneNumber(), dto.getChannel())
            .orElseGet(() -> subscriptionRepository.save(dto.toEntity()));

        // 구독 이력 저장
        subscriptionHistoryRepository.save(SubscriptionHistoryEntity.builder()
            .oldSubscriptionEntity(entity)
            .subscriptionStatus(dto.getSubscriptionStatus())
            .build());

        // 구독 상태 변경 및 저장
        entity.updateSubscriptionStatus(dto.getSubscriptionStatus(), Purpose.SUBSCRIBE);
        subscriptionRepository.save(entity);

    }

    @Transactional
    public void unsubscribe(UnsubscriptionDTO dto) {

        var entity = getAndLockSubscriptionOptional(dto.getPhoneNumber(), dto.getChannel())
            .orElseThrow(() -> new BaseException("channel not found"));

        // 구독 이력 저장
        subscriptionHistoryRepository.save(SubscriptionHistoryEntity.builder()
            .oldSubscriptionEntity(entity)
            .subscriptionStatus(dto.getSubscriptionStatus())
            .build());

        // 구독 상태 변경 및 저장
        entity.updateSubscriptionStatus(dto.getSubscriptionStatus(), Purpose.UNSUBSCRIBE);
        subscriptionRepository.save(entity);

    }


    public List<SubscriptionEntity> getSubscriptions(String phoneNumber) {
        return subscriptionRepository.findByPhoneNumber(phoneNumber);
    }


    public List<SubscriptionHistoryEntity> getSubscriptionHistory(String phoneNumber) {
        return subscriptionHistoryRepository.findByPhoneNumber(phoneNumber);
    }

    public List<SubscriptionHistoryEntity> getSubscriptionHistory(LocalDate date, Long channelId) {
        return subscriptionHistoryRepository.findByDateAndChannel(date, channelId);
    }

    private Optional<SubscriptionEntity> getAndLockSubscriptionOptional(String phoneNumber, ChannelEntity channel) {
        return subscriptionRepository.findAndLockByPhoneNumberAndChannel(phoneNumber, channel);
    }

}
