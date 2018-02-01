package repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import mongoDB.User;

@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<User,String>{

	List<User> findByAge(int i);

	List<User> findByNameOrderByAgeDesc(String string);

	

}
