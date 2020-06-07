package com.mits4u.transactionsdemo.service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NamesRepository extends JpaRepository<Name, Long> {

}
