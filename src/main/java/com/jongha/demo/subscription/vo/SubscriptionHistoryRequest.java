package com.jongha.demo.subscription.vo;

import com.jongha.demo.exception.BasePageRequest;
import com.jongha.demo.subscription.entity.SubscriptionHistoryEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionHistoryRequest extends BasePageRequest<SubscriptionHistoryEntity, Void> {

    @NotNull
    private String phoneNumber;
    @Nullable
    private Long channelId;

    @Override
    public Specification<SubscriptionHistoryEntity> toSpecification(Void empty) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(

                criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber),
                ObjectUtils.isEmpty(channelId) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("channel").get("id"), channelId)
            );
    }
}
