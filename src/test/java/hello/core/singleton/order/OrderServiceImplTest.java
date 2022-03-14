package hello.core.singleton.order;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderServiceImpl;

class OrderServiceImplTest {

	@Test
	void createOrder() {

		MemoryMemberRepository memberRepository = new MemoryMemberRepository();
		memberRepository.save(new Member(1L, "name", Grade.VIP));
		OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
		Order order = orderService.createOrder(1L, "itemA", 10000);
		assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

}