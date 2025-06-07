package com.likelion.likelionjwt.review.api.dto.request;

import jakarta.validation.constraints.*;

public record ReviewSaveRequestDto(
        @NotNull(message = "null 값이 들어오면 안 됩니다.")
        Long memberId,
        @Positive(message = "양수만 입력 가능합니다")
        @Min(value = 1, message = "최솟값은 1입니다.")
        @Max(value = 5, message = "최댓값은 5입니다.")
        int rating,
        @NotEmpty(message = "null 이거나 공백이면 안 됩니다.")
        String content
) {
}
