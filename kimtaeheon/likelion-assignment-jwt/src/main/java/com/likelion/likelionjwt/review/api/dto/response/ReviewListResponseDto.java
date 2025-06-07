package com.likelion.likelionjwt.review.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ReviewListResponseDto(
        List<ReviewInfoResponseDto> reviews
) {
    public static ReviewListResponseDto from(List<ReviewInfoResponseDto> reviews) {
        return ReviewListResponseDto.builder()
                .reviews(reviews)
                .build();
    }
}
