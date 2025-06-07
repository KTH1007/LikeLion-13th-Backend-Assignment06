package com.likelion.likelionjwt.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 수정되었습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "리뷰가 성공적으로 수정되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.OK, "사용자가 성공적으로 삭제되었습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, "리뷰가 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED
     */
    REVIEW_SAVE_SUCCESS(HttpStatus.CREATED, "리뷰가 성공적으로 생성되었습니다."),
    MEMBER_JOIN_SUCCESS(HttpStatus.CREATED, "회원가입에 성공하였습니다."),
    MEMBER_LOGIN_SUCCESS(HttpStatus.CREATED, "로그인에 성공하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    } //code 에서 숫자만 갖고 오는 메소드
}
