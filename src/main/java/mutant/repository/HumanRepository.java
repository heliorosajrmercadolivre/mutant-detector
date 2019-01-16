package mutant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mutant.domain.Human;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
	
}