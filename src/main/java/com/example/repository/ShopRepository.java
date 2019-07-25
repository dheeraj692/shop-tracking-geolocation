package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>{

}
