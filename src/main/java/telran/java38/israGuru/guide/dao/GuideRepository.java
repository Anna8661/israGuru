package telran.java38.israGuru.guide.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java38.israGuru.guide.model.Guide;

public interface GuideRepository extends MongoRepository<Guide, String> {

}
