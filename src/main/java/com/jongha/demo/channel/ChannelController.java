package com.jongha.demo.channel;

import com.jongha.demo.channel.vo.ChannelSubscriptionHistoryRequest;
import com.jongha.demo.channel.vo.ChannelSubscriptionHistoryResponse;
import com.jongha.demo.global.base.BaseResponse;
import com.jongha.demo.subscription.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ChannelController", description = "채널 API")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ChannelController {

    private final SubscriptionService subscriptionService;

    @Operation(summary = "채널 이력 조회", description = "특정 채널의 특정 일자의 구독 이력을 조회합니다.")
    @GetMapping(value = "/channel/subscription/history")
    public BaseResponse<ChannelSubscriptionHistoryResponse> getChannelHistories(
        @ModelAttribute @Valid ChannelSubscriptionHistoryRequest request) {
        var subscriptionHistories = subscriptionService.getSubscriptionHistory(request.toSpecification());
        return BaseResponse.ok(new ChannelSubscriptionHistoryResponse(subscriptionHistories));

    }
}
