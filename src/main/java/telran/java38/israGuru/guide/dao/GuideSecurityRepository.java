package telran.java38.israGuru.guide.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java38.israGuru.guide.model.GuideSecurity;

public interface GuideSecurityRepository extends MongoRepository<GuideSecurity, String> {

}
