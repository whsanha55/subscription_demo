package com.jongha.demo.subscription.facade;

import com.jongha.demo.channel.service.ChannelService;
import com.jongha.demo.subscription.service.SubscriptionService;
import com.jongha.demo.subscription.vo.SubscriptionRequest;
import com.jongha.demo.subscription.vo.UnsubscriptionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Slf4j
@Component
public class SubscriptionFacade {


    private final ChannelService channelService;
    private final SubscriptionService subscriptionService;

    @Transactional
    public void subscribe(SubscriptionRequest request) {
        var channel = channelService.getChannel(request.getChannelId());
        subscriptionService.subscribe(request.toDTO(channel));

    }

    @Transactional
    public void unsubscribe(UnsubscriptionRequest request) {
        var channel = channelService.getChannel(request.getChannelId());
        subscriptionService.subscribe(request.toDTO(channel));

    }

}
