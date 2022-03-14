package hello.core.singleton.order;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;

class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}

	@Test
	void createOrder() {
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId,"itemA", 10000);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}

	//필드 주입 테스트 코드 ( 수정자 주입 필요 )
	// @Test
	// void filedInjectionTest() {
	// 	OrderServiceImpl orderService = new OrderServiceImpl();
	//
	// 	orderService.setDiscountPolicy(new FixDiscountPolicy());
	// 	orderService.setMemberRepository(new MemoryMemberRepository());
	//
	// 	orderService.createOrder(1L, "itemA", 10000);
	//
	// }
}