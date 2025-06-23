package com.likelion.likelionjwt.review.api;

import com.likelion.likelionjwt.common.error.SuccessCode;
import com.likelion.likelionjwt.common.template.ApiResTemplate;
import com.likelion.likelionjwt.review.api.dto.request.ReviewSaveRequestDto;
import com.likelion.likelionjwt.review.api.dto.request.ReviewUpdateRequestDto;
import com.likelion.likelionjwt.review.api.dto.response.ReviewInfoResponseDto;
import com.likelion.likelionjwt.review.api.dto.response.ReviewListResponseDto;
import com.likelion.likelionjwt.review.application.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/save")
    public ApiResTemplate<String> reviewSave(@RequestBody @Valid ReviewSaveRequestDto requestDto, Principal principal) {
        reviewService.saveReview(requestDto, principal);
        return ApiResTemplate.successWithNoContent(SuccessCode.REVIEW_SAVE_SUCCESS);
    }

    @GetMapping()
    public ApiResTemplate<ReviewListResponseDto> reviewFindMember(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                                               Principal principal) {
        ReviewListResponseDto reviewListResponseDto = reviewService.reviewFindMember(pageable, principal);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, reviewListResponseDto);
    }

    @GetMapping("/{id}")
    public ApiResTemplate<ReviewInfoResponseDto> reviewFindOne(@PathVariable("id") Long id) {
        ReviewInfoResponseDto reviewInfoResponseDto = reviewService.reviewFindById(id);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, reviewInfoResponseDto);
    }

    // 리뷰 수정
    @PatchMapping("/{id}")
    public ApiResTemplate<String> reviewUpdate(@PathVariable("id") Long id,
                                            @RequestBody @Valid ReviewUpdateRequestDto reviewUpdateRequestDto, Principal principal) {
        reviewService.reviewUpdate(id, reviewUpdateRequestDto, principal);
        return ApiResTemplate.successWithNoContent(SuccessCode.REVIEW_UPDATE_SUCCESS);
    }

    // 리뷰 삭제
    @DeleteMapping("/{id}")
    public ApiResTemplate<String> reviewDelete(@PathVariable("id") Long id, Principal principal) {
        reviewService.reviewDelete(id, principal);
        return ApiResTemplate.successWithNoContent(SuccessCode.REVIEW_DELETE_SUCCESS);
    }
}
