package com.likelion.likelionjwt.review.application;

import com.likelion.likelionjwt.common.error.ErrorCode;
import com.likelion.likelionjwt.common.exception.BusinessException;
import com.likelion.likelionjwt.member.domain.Member;
import com.likelion.likelionjwt.member.domain.repository.MemberRepository;
import com.likelion.likelionjwt.review.api.dto.request.ReviewSaveRequestDto;
import com.likelion.likelionjwt.review.api.dto.request.ReviewUpdateRequestDto;
import com.likelion.likelionjwt.review.api.dto.response.ReviewInfoResponseDto;
import com.likelion.likelionjwt.review.api.dto.response.ReviewListResponseDto;
import com.likelion.likelionjwt.review.domain.Review;
import com.likelion.likelionjwt.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void saveReview(ReviewSaveRequestDto requestDto) {
        Member member = getMember(requestDto.memberId());
        Review review = Review.builder()
                .rating(requestDto.rating())
                .content(requestDto.content())
                .member(member)
                .build();

        reviewRepository.save(review);
    }

    public ReviewInfoResponseDto reviewFindById(Long id) {
        Review review = getReview(id);
        return ReviewInfoResponseDto.from(review);
    }

    public ReviewListResponseDto reviewFindMember(Long id, Pageable pageable) {
        Member member = getMember(id);

        // 사용자가 작성한 리뷰를 페이지로 리턴
        Page<Review> reviews = reviewRepository.findPageByMemberMemberId(id, pageable);
        List<ReviewInfoResponseDto> reviewInfoResponseDtos = reviews.stream()
                .map(ReviewInfoResponseDto::from)
                .toList();

        return ReviewListResponseDto.from(reviewInfoResponseDtos);
    }

    // 리뷰 수정
    @Transactional
    public void reviewUpdate(Long id, ReviewUpdateRequestDto reviewUpdateRequestDto) {
        Review review = getReview(id);
        review.update(reviewUpdateRequestDto);
    }

    // 리뷰 삭제
    @Transactional
    public void reviewDelete(Long id) {
        Review review = getReview(id);
        reviewRepository.delete(review);
    }

    private Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.REVIEW_NOT_FOUND_EXCEPTION,
                        ErrorCode.REVIEW_NOT_FOUND_EXCEPTION.getMessage()));
    }

    private Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND_EXCEPTION,
                ErrorCode.MEMBER_NOT_FOUND_EXCEPTION.getMessage()));
    }
}
