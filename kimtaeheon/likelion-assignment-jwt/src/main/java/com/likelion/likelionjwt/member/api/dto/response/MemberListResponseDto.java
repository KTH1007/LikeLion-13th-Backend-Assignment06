package com.likelion.likelionjwt.member.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MemberListResponseDto(
        List<MemberInfoResDto> members
) {
    public static MemberListResponseDto from(List<MemberInfoResDto> members) {
        return MemberListResponseDto.builder()
                .members(members)
                .build();
    }
}
