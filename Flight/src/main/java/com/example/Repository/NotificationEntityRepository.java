package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.NotificationEntity;

public interface NotificationEntityRepository extends JpaRepository<NotificationEntity, Integer>{

}
