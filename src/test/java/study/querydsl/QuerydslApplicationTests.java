package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	// 프로젝트 생성 후 라이브러리 검증 테스트
	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);

//		QHello qHello = new QHello("h");
	    QHello qHello = QHello.hello; //위와 같음

		Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		assertThat(result).isEqualTo(hello); // querydsl 설정 검증
		assertThat(result.getId()).isEqualTo(hello.getId()); // lombok 검증

	}

}
