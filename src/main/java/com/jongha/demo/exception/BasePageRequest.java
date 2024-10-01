package com.jongha.demo.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;


@Slf4j
@Data
@NoArgsConstructor
public abstract class BasePageRequest<T, K> {


    protected static final Sort DEFAULT_SORT = Sort.by(Direction.DESC, "id");

    @Schema(description = "페이지 번호", example = "1")
    protected int page = 1;
    @Schema(description = "페이지 크기", example = "20")
    protected int size = 20;

    public PageRequest toPageRequest() {
        return PageRequest.of(Math.max(page - 1, 0), size, sort());
    }

    // 추가 파라미터가 필요하지 않는 경우 사용한다.
    public Specification<T> toSpecification() {
        return toSpecification(null);
    }

    public abstract Specification<T> toSpecification(K additional);

    /**
     * 기본값은 id DESC이며, 필요한 경우 override 하여 사용한다.
     **/
    public Sort sort() {
        return DEFAULT_SORT;
    }


}
