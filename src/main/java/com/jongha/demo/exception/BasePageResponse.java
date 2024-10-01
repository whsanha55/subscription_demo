package com.jongha.demo.exception;

import java.util.List;
import java.util.function.Function;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;


@Slf4j
@Data
@NoArgsConstructor
public class BasePageResponse<T> {

    private int page;
    private int size;
    private int totalPageCount;

    protected long totalElementCount;
    protected boolean hasNext;

    private List<T> list;

    public <O> BasePageResponse(Page<O> page, Function<O, T> function) {
        this.page = Math.max(page.getNumber(), 1);
        this.size = page.getSize();
        this.totalPageCount = page.getTotalPages();
        this.totalElementCount = page.getTotalElements();
        this.hasNext = page.hasNext();
        this.list = page.get()
            .map(function)
            .toList();
    }

}
