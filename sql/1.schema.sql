drop table if exists  channel;
drop table if exists  subscription;
drop table if exists  subscription_history;

CREATE TABLE channel
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '채널 고유 ID',
    channel_name      VARCHAR(100) NOT NULL COMMENT '채널 이름 (플랫폼, 사이트명)',
    able_subscribe TINYINT not null comment '구독 가능 여부',
    able_unsubscribe TINYINT not null comment '구독 해지 가능 여부',
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT '구독/해지 가능한 외부 서비스 채널을 관리하는 테이블';


CREATE TABLE subscription
(
    id                  INT AUTO_INCREMENT PRIMARY KEY COMMENT '구독 상태 고유 ID',
    phone_number        VARCHAR(256) NOT NULL COMMENT '회원 고유 ID(휴대폰 번호 암호화)',
    channel_id          INT          NOT NULL COMMENT '채널 고유 ID',
    subscription_status VARCHAR(50)  NOT NULL COMMENT '회원 구독 상태 (none: 구독 안함, normal: 일반 구독, premium: 프리미엄 구독)',
    created_at          DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    index (phone_number)
) COMMENT '회원의 구독 상태 관리 테이블';

CREATE TABLE subscription_history
(
    id                      INT AUTO_INCREMENT PRIMARY KEY COMMENT '구독 이력 고유 ID',
    phone_number         VARCHAR(256)         NOT NULL COMMENT '회원 고유 ID(휴대폰 번호 암호화)',
    channel_id              INT         NOT NULL COMMENT '채널 고유 ID',
    old_subscription_status VARCHAR(50) NOT NULL COMMENT '이전 구독 상태 (none, normal, premium)',
    subscription_status     VARCHAR(50) NOT NULL COMMENT '변경된 구독 상태 (none, normal, premium)',
    created_at              DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at              DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간',
    index (phone_number),
    index (channel_id)
) COMMENT '구독 상태 변경 이력 관리 테이블';
