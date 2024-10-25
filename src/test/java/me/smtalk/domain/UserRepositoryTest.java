package me.smtalk.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 이름에 맞는 사용자를 가져올 수 있다.")
    void findByUsername01() {
        User user01 = User.builder()
                .id("manijang2")
                .password("1234")
                .name("김선만1")
                .build();

        User user02 = User.builder()
                .id("manijang3")
                .password("1234")
                .name("김선만2")
                .build();

        userRepository.saveAll(List.of(user01, user02));

        List<User> users = userRepository.findByName("김선만1");

        assertThat(users)
                .hasSize(1)
                .extracting("id", "password", "name")
                .containsExactlyInAnyOrder(
                        tuple("manijang2", "1234", "김선만1")
                );
    }

    @Test
    @DisplayName("사용자 이름이 없을 경우 빈 리스트를 반환한다.")
    void findByUsername02() {
        User user01 = User.builder()
                .id("manijang2")
                .password("1234")
                .name("김선만1")
                .build();

        User user02 = User.builder()
                .id("manijang3")
                .password("1234")
                .name("김선만2")
                .build();

        userRepository.saveAll(List.of(user01, user02));

        List<User> users = userRepository.findByName("김선만3");

        assertThat(users).isEmpty();
    }
}