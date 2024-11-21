package com.xcode_software.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    default T findByIdWithException(ID id) {
        return findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entity not found with ID: " + id));
    }
}

