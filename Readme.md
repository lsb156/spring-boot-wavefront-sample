# Wavefront
[Wavefront By vmware](https://docs.wavefront.com/wavefront_springboot.html)

[Spring Tips: The Wavefront Observability Platform](https://www.youtube.com/watch?v=R8RAgTJvbEc)

현재 서버의 상태, Request 횟수, Error 현황, Request Latency 등을 실시간으로 나타내주며
JVM의 CG, Thread Count, Heap Size 등이 실시간으로 확인이 가능하다.
여러대의 서버에 등록을 해놓으면 각 서비스별 현황이 확인 가능하며
서버 요청 처리 시간, Http 요청등을 상세하게 볼 수 있다.

`Wavefront`는 어플리케이션이 가동중인 서버 내에 설치되어 동작되는 방식이 아니라
클라우드에 이벤트가 전송되어 web상으로 실시간 확인이 가능한 솔루션이다.

## 일반 설정

### Spring Initializr Dependencies

`Java 11`, `Maven`, `Spring Boot 2.3.0` 

- Sleuth 
- Spring Reactive Web
- Spring Data R2DBC
- PostgreSQL Driver
- Lombok


### Properties 추가
```properties
spring.r2dbc.url=r2dbc:postgresql://localhost/{Database}
spring.r2dbc.username={UserName}
spring.r2dbc.password={Password}

wavefront.application.name={Project Name}
wavefront.application.service={Microservice Name}
```
 



### Dependencies 추가 설정 
`pom.xml`에 추가하여야 할 사항
```xml
    <dependencies>
        ...
        <dependency>
            <groupId>com.wavefront</groupId>
            <artifactId>wavefront-spring-boot-starter</artifactId>
            <version>2.0.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>sonatype-snapshots</id>
            <name>Sonatype Snapshos</name>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        </repository>
    </repositories>
```

### Database 생성
PostgresQL을 사용하여 테이블을 생성하여준다.
```sql
-- postgresql
CREATE TABLE public.reservation
(
    name character varying COLLATE pg_catalog."default",
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 )
)
TABLESPACE pg_default;
```

## 실행 후 토큰 얻기 
실행 후 다음 메시지와 함께 토큰과 접속 주소가 생성된다.
토큰은 `~/.wavefront_freemium` 경로와 파일명으로 생성된다.
```xml
A Wavefront account has been provisioned successfully and the API token has been saved to disk.

To share this account, make sure the following is added to your configuration:

	management.metrics.export.wavefront.api-token={API TOKEN}
	management.metrics.export.wavefront.uri=https://wavefront.surf

Connect to your Wavefront dashboard using this one-time use link:
https://wavefront.surf/us/{ONE_TIME_LINK}
```