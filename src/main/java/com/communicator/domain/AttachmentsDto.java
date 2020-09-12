package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentsDto {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileExtension;
}
