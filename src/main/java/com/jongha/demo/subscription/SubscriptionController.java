package com.jongha.demo.subscription;

import com.jongha.demo.global.base.BaseResponse;
import com.jongha.demo.subscription.facade.SubscriptionFacade;
import com.jongha.demo.subscription.service.SubscriptionService;
import com.jongha.demo.subscription.vo.SubscriptionHistoryResponse;
import com.jongha.demo.subscription.vo.SubscriptionRequest;
import com.jongha.demo.subscription.vo.UnsubscriptionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SubscriptionController", description = "구독 API")
@RequiredArgsConstructor
@RestController
@Slf4j
public class SubscriptionController {

    private final SubscriptionFacade subscriptionFacade;
    private final SubscriptionService subscriptionService;

    @Operation(summary = "구독하기", description = "채널을 구독합니다.")
    @PostMapping(value = "/subscription")
    public BaseResponse<Void> subscription(@RequestBody @Valid SubscriptionRequest request) {
        subscriptionFacade.subscribe(request);
        return BaseResponse.ok();
    }

    @Operation(summary = "구독 해지", description = "구독된 채널을 해지합니다.")
    @PostMapping(value = "/unsubscription")
    public BaseResponse<Void> unsubscription(@RequestBody @Valid UnsubscriptionRequest request) {
        subscriptionFacade.unsubscribe(request);
        return BaseResponse.ok();
    }

    @Operation(summary = "구독 이력 조회", description = "특정 유저의 구독 이력을 조회합니다.")
    @GetMapping(value = "/subscription/user/history")
    public BaseResponse<SubscriptionHistoryResponse> getSubscriptionList(@RequestParam String phoneNumber) {
        var subscriptionHistory = subscriptionService.getSubscriptionHistory(phoneNumber);
        return BaseResponse.ok(new SubscriptionHistoryResponse(subscriptionHistory));

    }

    @Operation(summary = "채널 이력 조회", description = "특정 채널의 특정 일자의 구독 이력을 조회합니다.")
    @GetMapping(value = "/subscription/channel/history")
    public BaseResponse<SubscriptionHistoryResponse> getChannelHistories(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @RequestParam Long channelId) {
        var subscriptionHistories = subscriptionService.getSubscriptionHistory(date, channelId);
        return BaseResponse.ok(new SubscriptionHistoryResponse(subscriptionHistories));

    }
}
