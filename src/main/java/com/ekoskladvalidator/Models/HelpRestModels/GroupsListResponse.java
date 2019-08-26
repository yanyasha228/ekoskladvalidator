package com.ekoskladvalidator.Models.HelpRestModels;

import com.ekoskladvalidator.Models.DTO.GroupDto;
import com.ekoskladvalidator.Models.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GroupsListResponse {

    private List<GroupDto> groups = new ArrayList<GroupDto>();

    private ErrorsResponse errors;
}
