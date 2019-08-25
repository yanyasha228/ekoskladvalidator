package com.ekoskladvalidator.Models.HelpRestModels;

import com.ekoskladvalidator.Models.Group;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GroupsRestList {

    private List<Group> groups = new ArrayList<Group>();

}
