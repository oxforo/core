package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;

@Component
public class OrderServiceImpl implements OrderService{

	// 생성자 주입
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	/*
	// 필드 주입
	@Autowired private MemberRepository memberRepository;
	@Autowired private DiscountPolicy discountPolicy;
	*/

	/*
	// 수정자 수입
	private MemberRepository memberRepository;
	private DiscountPolicy discountPolicy;

	@Autowired
	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		System.out.println("discountPolicy = " + discountPolicy);
		this.discountPolicy = discountPolicy;
	}

	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		System.out.println("memberRepository = " + memberRepository);
		this.memberRepository = memberRepository;
	}*/



	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
