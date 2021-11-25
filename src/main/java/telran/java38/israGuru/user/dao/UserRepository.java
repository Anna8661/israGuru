package telran.java38.israGuru.user.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java38.israGuru.user.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
