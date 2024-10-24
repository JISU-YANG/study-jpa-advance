package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.Food;
import com.sparta.jpaadvance.entity.User;
import com.sparta.jpaadvance.repository.FoodRepository;
import com.sparta.jpaadvance.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class OneToManyTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("1대N 단방향 테스트")
    void test1() {
        User user = new User();
        user.setName("Robbie");

        User user2 = new User();
        user2.setName("Robbert");

        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);
        food.getUserList().add(user); // 외래 키(연관 관계) 설정
        food.getUserList().add(user2); // 외래 키(연관 관계) 설정

        userRepository.save(user);
        userRepository.save(user2);
        foodRepository.save(food);

        // 추가적인 UPDATE 쿼리 발생을 확인할 수 있습니다.
    }

    @Test
    @DisplayName("1대N 조회 테스트")
    void test2() {
        Food food = foodRepository.findById(1L).orElseThrow(NullPointerException::new);
        System.out.println("food.getName() = " + food.getName());

        // 해당 음식을 주문한 고객 정보 조회
        List<User> userList = food.getUserList();
        for (User user : userList) {
            System.out.println("user.getName() = " + user.getName());
        }
    }
}