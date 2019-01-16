package mutant.service;

import java.util.List;

import mutant.domain.Human;
import mutant.exception.InvalidDnaException;
import mutant.exception.InvalidNitrogenousBasesException;

/**
 * Human service interface with base methods that must be implemented.
 * 
 * @author Hélio De Rosa Junior
 */
public interface HumanService {

	public boolean isMutant(String[] dnaSequence) throws InvalidDnaException, InvalidNitrogenousBasesException;
	
	public void validateDna() throws InvalidDnaException;
	
	public void validateNitrogenousBases() throws InvalidNitrogenousBasesException;
	
	public long createDnaId();
	
	public long save(Human human);
	
	public List<Human> findAll();
}

