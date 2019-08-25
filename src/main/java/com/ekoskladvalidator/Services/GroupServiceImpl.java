package com.ekoskladvalidator.Services;

import com.ekoskladvalidator.Dao.GroupDao;
import com.ekoskladvalidator.Models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public Group save(Group group) {
        return groupDao.save(group);
    }

    @Override
    public List<Group> save(List<Group> groupList) {
        return groupDao.saveAll(groupList);
    }

    @Override
    public Optional<Group> findById(int id) {
        return groupDao.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }


}
