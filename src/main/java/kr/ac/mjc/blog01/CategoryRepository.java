package kr.ac.mjc.blog01;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Override
    List<Category> findAll();
}
