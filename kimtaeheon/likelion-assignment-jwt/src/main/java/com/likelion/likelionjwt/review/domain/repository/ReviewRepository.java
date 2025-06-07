package com.likelion.likelionjwt.review.domain.repository;

import com.likelion.likelionjwt.member.domain.Member;
import com.likelion.likelionjwt.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMember(Member member);

    Page<Review> findPageByMemberMemberId(Long memberId, Pageable pageable);
}
