INSERT INTO channel (channel_name, able_subscribe, able_unsubscribe)
VALUES ('뉴스 채널', 1, 1),
       ('음악 채널', 1, 0),
       ('영화 채널', 1, 1),
       ('스포츠 채널', 1, 1),
       ('어린이 채널', 1, 0),
       ('프리미엄 스포츠 채널', 0, 1),
       ('기술 채널', 1, 1),
       ('다큐멘터리 채널', 1, 0),
       ('게임 채널', 1, 1),
       ('교육 채널', 1, 1);

INSERT INTO subscription (phone_number, channel_id, subscription_status)
VALUES ('MQ==', 1, 'PREMIUM'),
       ('MQ==', 2, 'REGULAR'),
       ('MQ==', 3, 'NONE');


INSERT INTO subscription_history (phone_number, channel_id, old_subscription_status, subscription_status)
VALUES ('MQ==', 1, 'NONE', 'PREMIUM' ),
       ('MQ==', 2, 'NONE', 'PREMIUM' ),
       ('MQ==', 3, 'NONE', 'PREMIUM' );
