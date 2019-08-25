package com.ekoskladvalidator.Services;

import com.ekoskladvalidator.Models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group save(Group group);

    List<Group> save(List<Group> groupList);

    Optional<Group> findById(int id);

    List<Group> findAll();

}
