package com.example.fitnessmeal.FitnessMealService.Repo;

import com.example.fitnessmeal.FitnessMealService.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
