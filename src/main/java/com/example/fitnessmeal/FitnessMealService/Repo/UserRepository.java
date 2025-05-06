package com.example.fitnessmeal.FitnessMealService.Repo;

import com.example.fitnessmeal.FitnessMealService.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
