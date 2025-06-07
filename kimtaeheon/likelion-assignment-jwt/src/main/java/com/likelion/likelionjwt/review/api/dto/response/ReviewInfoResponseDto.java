package com.likelion.likelionjwt.review.api.dto.response;

import com.likelion.likelionjwt.review.domain.Review;
import lombok.Builder;

@Builder
public record ReviewInfoResponseDto(
        int rating,
        String content
) {
    public static ReviewInfoResponseDto from(Review review) {
        return ReviewInfoResponseDto.builder()
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }
}
