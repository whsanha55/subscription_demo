package com.jongha.demo.channel.vo;

import com.jongha.demo.exception.BasePageRequest;
import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ObjectUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelSubscriptionHistoryRequest extends BasePageRequest<SubscriptionHistoryEntity, Void> {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Nullable
    private Long channelId;

    @Override
    public Specification<SubscriptionHistoryEntity> toSpecification(Void empty) {
        return (root, query, criteriaBuilder) -> {
            root.fetch("channel");
            return criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), date.atStartOfDay()),
                criteriaBuilder.lessThan(root.get("createdAt"), date.plusDays(1).atStartOfDay()),
                ObjectUtils.isEmpty(channelId) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("channel").get("id"), channelId)
            );
        };
    }
}
