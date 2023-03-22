package com.woody.client.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

/**
 * Created by woody 2023.03.22
 * Request 로그 출력용 Filter
 * */
@Slf4j
public class LogRequestFilter implements ExchangeFilterFunction {
    @Override
    public Mono<ClientResponse> filter(ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
        if (log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder("Request: ");
            sb.append(clientRequest.method());
            sb.append(" ");
            sb.append(clientRequest.url());
            log.debug(sb.toString());
        }
        return exchangeFunction.exchange(clientRequest);
    }
}
