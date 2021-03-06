package com.mits4u.transactionsdemo.service.jpa;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NamesDao {

    @Resource
    private NamesRepository namesRepository;

    public void save(Name name) {
        namesRepository.save(name);
    }

}
