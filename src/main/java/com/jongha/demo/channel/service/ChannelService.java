package com.jongha.demo.channel.service;

import com.jongha.demo.channel.entity.ChannelEntity;
import com.jongha.demo.channel.repository.ChannelRepository;
import com.jongha.demo.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChannelService {


    private final ChannelRepository channelRepository;

    public ChannelEntity createChannel(String channelName, boolean ableSubscribe, boolean ableUnsubscribe) {
        return channelRepository.save(ChannelEntity.builder()
            .channelName(channelName)
            .ableSubscribe(ableSubscribe)
            .ableUnsubscribe(ableUnsubscribe)
            .build());
    }

    public ChannelEntity getChannel(Long channelId) {
        return channelRepository.findById(channelId)
            .orElseThrow(() -> new BaseException("Channel not found"));

    }
}
