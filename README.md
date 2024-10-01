# 구독 API 데모 

## 실행 방법

### 1. Docker를 통한 MySQL 서버 실행
[MySQL Dockerfile - ./docker/docker-compose.yml](https://github.com/whsanha55/subscription_demo/blob/main/docker/docker-compose.yml)

MySQL 서버는 Docker를 통해 실행됩니다. 기본적으로 **3306 포트**는 다른 서비스에서 사용 중일 가능성이 높으므로, **3400 포트**를 사용합니다.

```bash
# 실행 방법
cd ./docker
docker-compose up -d
```

### 2. 데이터베이스 스키마 및 데이터 초기화
#### docker-compose 에서 자동 실행하게끔 설정되어 있습니다.
[데이터베이스 스키마 - ./sql/1.schema.sql](https://github.com/whsanha55/subscription_demo/blob/main/sql/1.schema.sql)

[데이터 - ./sql/2.data.sql](https://github.com/whsanha55/subscription_demo/blob/main/sql/2.data.sql)

### 3. API 문서 확인
Swagger를 사용하여 API 문서를 확인할 수 있습니다.
- [Swagger API 문서](http://localhost:8080/swagger-ui/index.html)


## 특이사항
 
> 휴대폰 번호를 사용하여 유저를 식별하고, 구독 및 구독 해지, 구독 이력 조회 등의 기능을 담당합니다. <br>
> 그러나 다중 폰이나 번호 변경의 이유로 고유성이 보장되지 않으므로, **토큰 기반 인증** 또는 **API Gateway**를 통한 인증과 라우팅 처리가 필요합니다. <br> 
> 현재는 **데모 특성상 인증 기능**을 생략하였습니다.

> 데이터베이스에 저장된 휴대폰 번호가 그대로 노출되지 않도록 **Base64 인코딩**을 통해 간단한 암호화 작업을 수행했습니다. <br> 
> 하지만 보안성이 낮기 때문에 추후 **AES256** 등의 강력한 암호화 알고리즘으로 변경할 필요가 있습니다. 

## 주요 기술 스택
- **Java 17**
- **Spring Boot 3.3.4**
- **JPA**
- **MySQL8**
- **Swagger**
