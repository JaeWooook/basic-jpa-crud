package com.example.crudjpa.noticeboard.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
@Getter
public class BoardDtlResponseDTO {
    private String boardFlag;
}
