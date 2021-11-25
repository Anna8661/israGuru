package telran.java38.israGuru.user.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java38.israGuru.user.model.UserSecurity;

public interface UserSecurityRepository extends MongoRepository<UserSecurity, String> {

}
