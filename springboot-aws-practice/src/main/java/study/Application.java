package study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
스프링부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정된다.
특히, @SpringBootApplication부터 설정을 읽어가기 때문에
이 클래스는 항상 프로젝트의 최상단에 위치해야한다.
* */
public class Application { /* 메인 클래스 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // WAS 를 실행. 항상 서버에 톰캣을 설치할 필요 없이, 스프링 부트로 만들어진 jar 파일로 실행하면 된다.
    }
}
