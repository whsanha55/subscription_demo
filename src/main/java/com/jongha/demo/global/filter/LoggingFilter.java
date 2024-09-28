package com.jongha.demo.global.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class LoggingFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
        var start = System.currentTimeMillis();
        var requestWrapper = new ContentCachingRequestWrapper(request);

        chain.doFilter(requestWrapper, response);

        var end = System.currentTimeMillis();
        var requestURI = request.getRequestURI();

        log.info("[REQUEST] {} {},  [TIME] : {}, [PARAMS] : {} , [BODY] : {}, [HEADER] : {}",
            request.getMethod(),
            requestURI,
            (end - start),
            objectMapper.writeValueAsString(request.getParameterMap()),
            getRequestBody(requestWrapper),
            getHeaders(request));

    }


    private Map<String, String> getHeaders(HttpServletRequest request) {
        var headerMap = new HashMap<String, String>();

        var headerArray = request.getHeaderNames();
        while (headerArray.hasMoreElements()) {
            var headerName = String.valueOf(headerArray.nextElement());
            headerMap.put(headerName, request.getHeader(headerName));
        }

        return headerMap;
    }

    private String getRequestBody(ServletRequest request) {
        try {
            var wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            if (wrapper != null) {
                return new String(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            }
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
        return "";
    }

}
