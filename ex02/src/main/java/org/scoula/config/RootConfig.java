package org.scoula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 🌱 Root Application Context 설정 클래스
 * - Spring Framework의 최상위(Root) 애플리케이션 컨텍스트를 설정하는 클래스
 * - 웹 계층과 무관한 비즈니스 로직, 서비스, 데이터 액세스 계층의 Bean들을 관리
 */
@Configuration
@ComponentScan(basePackages = {"org.scoula"})
public class RootConfig {
}
