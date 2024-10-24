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

@Transactional // 모든 메서드에 적용
@SpringBootTest
public class OneToOneTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;

    @Test
//    @Transactional // 자식 메서드에 @Transactional 이 적용되어 있지만 이 필드에서는 영속성 컨텍스트가 감지 할 수 없기때문에 적용해줘야 변경 감지 가능하다.
    @Rollback(value = false) // 테스트에서는 @Transactional 에 의해 자동 rollback 됨으로 false 설정해준다.
    void test(){
        Food food = foodRepository.findById(1L).orElse(null);
        food.setName("피자");
    }

    @Test
    @Rollback(value = false)
    @DisplayName("1대1 단방향 테스트")
    void test1() {

        User user = new User();
        user.setName("Robbie");

        // 외래 키의 주인인 Food Entity user 필드에 user 객체를 추가해 줍니다.
        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);
        food.setUser(user); // 외래 키(연관 관계) 설정

        userRepository.save(user);
        foodRepository.save(food);
    }

}
