package com.communicator.mapper;

import com.communicator.domain.Attachments;
import com.communicator.domain.AttachmentsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttachmentsMapper {
    Attachments mapToAttachments(AttachmentsDto attachmentsDto);
    AttachmentsDto mapToAttachmentsDto(Attachments attachments);
    List<Attachments> mapToAttachmentsList(List<AttachmentsDto> attachmentsDtoList);
    List<AttachmentsDto> mapToAttachmentsDtoList(List<Attachments> attachmentsList);
}
