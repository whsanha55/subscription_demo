package com.jongha.demo;

import autoparams.AutoSource;
import com.jongha.demo.channel.entity.ChannelEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @ParameterizedTest
    @AutoSource
    void contextLoads(ChannelEntity channelEntity) {
        log.info("channelEntity : {}", channelEntity);
    }

}
