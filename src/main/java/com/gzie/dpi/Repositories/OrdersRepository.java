package com.gzie.dpi.Repositories;

import com.gzie.dpi.Entities.OrderEntity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity,Long> {
}
