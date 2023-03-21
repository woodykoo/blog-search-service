# 블로그 검색 서비스

<div>
	<img src="https://img.shields.io/badge/Spring%20Boot-2.7.8-orange" />
    <img src="https://img.shields.io/badge/-Java11-blue" />
	<img src="https://img.shields.io/badge/-Gradle-green" />
</div>


### API 명세서
https://documenter.getpostman.com/view/4637707/2s93RKzFWa

### 실행 Jar 다운로드 링크
https://github.com/woodykoo/blog-search-service/raw/master/download/blog-search-service.jar


### 외부 라이브러리 및 오픈소스
```
Redis : 로컬 환경에서 실행시키기 윈한 Embedded Redis 사용
implementation "com.github.codemonstur:embedded-redis:0.13.0"

Redssion : RedisClient로 동시성 이슈 제어를 위한 Redis 분산락 사용
implementation 'org.redisson:redisson:3.20.0'

Openfeign : 백엔드 API 연동을 위한 Client로 사용
implementation 'org.springframework.cloud:spring-cloud-starter-openfeign'
```

### 프로젝트 구조
```
.
├── HELP.md
├── README.md
├── build.gradle
├── client
│   ├── build.gradle
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── woody
│           │           └── client
│           │               ├── config
│           │               │   └── FeignConfig.java
│           │               ├── kakao
│           │               │   ├── KakaoBlogSearchClient.java
│           │               │   ├── config
│           │               │   │   └── KakaoFeignConfig.java
│           │               │   ├── dto
│           │               │   │   └── response
│           │               │   │       └── KakaoBlogSearchResponse.java
│           │               │   └── exception
│           │               │       ├── KakaoBadRequestException.java
│           │               │       ├── KakaoClientException.java
│           │               │       ├── KakaoErrorResponse.java
│           │               │       ├── KakaoFeignErrorDecoder.java
│           │               │       └── KakaoServerErrorException.java
│           │               └── naver
│           │                   ├── NaverBlogSearchClient.java
│           │                   ├── config
│           │                   │   └── NaverFeignConfig.java
│           │                   ├── dto
│           │                   │   └── response
│           │                   │       └── NaverBlogSearchResponse.java
│           │                   └── exception
│           │                       ├── NaverBadRequestException.java
│           │                       ├── NaverClientException.java
│           │                       ├── NaverErrorResponse.java
│           │                       ├── NaverFeignErrorDecoder.java
│           │                       └── NaverServerErrorException.java
│           └── resources
│               └── application-client.yml
├── domain
│   ├── build.gradle
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── woody
│           │           └── domain
│           │               ├── aop
│           │               │   ├── RedissonCallTransaction.java
│           │               │   ├── RedissonLockAop.java
│           │               │   └── annotaion
│           │               │       └── RedissonLock.java
│           │               ├── blog
│           │               │   ├── entity
│           │               │   │   └── BlogSearchStatistics.java
│           │               │   ├── repository
│           │               │   │   ├── BlogSearchStatisticsQueryRepository.java
│           │               │   │   └── BlogSearchStatisticsRepository.java
│           │               │   └── service
│           │               │       ├── BlogSearchStatisticsManager.java
│           │               │       └── BlogSearchStatisticsReader.java
│           │               └── config
│           │                   ├── JpaConfig.java
│           │                   ├── QueryDslConfig.java
│           │                   └── RedisConfig.java
│           └── resources
│               └── application-domain.yml
├── download
│   └── blog-search-service.jar
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
└── webservice
    ├── build.gradle
    └── src
        └── main
            ├── java
            │   └── com
            │       └── woody
            │           └── webservice
            │               ├── BlogSearchServiceApplication.java
            │               ├── blogsearch
            │               │   ├── api
            │               │   │   ├── BlogSearchApi.java
            │               │   │   ├── BlogSearchStatisticsApi.java
            │               │   │   ├── request
            │               │   │   │   └── BlogSearchRequest.java
            │               │   │   └── response
            │               │   │       ├── BlogPopularSearchesResponse.java
            │               │   │       └── BlogSearchResponse.java
            │               │   ├── enums
            │               │   │   ├── BlogSearchSource.java
            │               │   │   └── Sort.java
            │               │   ├── event
            │               │   │   ├── BlogSearchStatisticsEvent.java
            │               │   │   └── BlogSearchStatisticsEventListener.java
            │               │   └── service
            │               │       ├── BlogSearchService.java
            │               │       ├── BlogSearchStatisticsService.java
            │               │       ├── data
            │               │       │   ├── BlogPopularSearchData.java
            │               │       │   ├── BlogSearchConditionData.java
            │               │       │   └── BlogSearchResultData.java
            │               │       ├── impl
            │               │       │   ├── BlogSearchStatisticsServiceImpl.java
            │               │       │   ├── KakaoBlogSearchServiceImpl.java
            │               │       │   └── NaverBlogSearchServiceImpl.java
            │               │       └── router
            │               │           └── BlogSearchServiceRouter.java
            │               ├── config
            │               │   └── AsyncConfig.java
            │               └── exception
            │                   ├── ApiExceptionHandler.java
            │                   ├── ErrorCode.java
            │                   ├── ErrorResponse.java
            │                   └── NotFoundServiceException.java
            └── resources
                ├── application.yml
                └── logback-spring.xml

56 directories, 67 files
```