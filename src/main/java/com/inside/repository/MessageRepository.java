package com.inside.repository;

import com.inside.model.Message;
import com.inside.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySender(User sender, Pageable pageable);

}
