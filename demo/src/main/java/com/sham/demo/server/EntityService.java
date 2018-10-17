package com.sham.demo.server;

import com.sham.demo.model.EntityDemo;
import com.sham.mongodb.dao.EntityDao;
import org.springframework.stereotype.Service;

@Service
public class EntityService extends EntityDao<EntityDemo> {
}
