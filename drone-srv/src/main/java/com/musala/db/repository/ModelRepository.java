/**
 * 
 */
package com.musala.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.db.entity.Model;

/**
 * @author aeltayary
 *
 */
public interface ModelRepository extends JpaRepository<Model, Integer> {

}
