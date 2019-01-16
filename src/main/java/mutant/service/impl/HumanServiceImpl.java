package mutant.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mutant.domain.DNA;
import mutant.domain.Human;
import mutant.exception.InvalidDnaException;
import mutant.exception.InvalidNitrogenousBasesException;
import mutant.repository.HumanRepository;
import mutant.service.HumanService;

/**
 * Human service implementation with the required methods to validate a DNA
 * sequence and determine if the human is a mutant or not.
 * 
 * @author Hélio De Rosa Junior
 */
@Service
public class HumanServiceImpl implements HumanService {

	/**
	 * DNA domain class autowired to store and give access to the DNA data
	 * properly.
	 */
	private DNA dna;
	
	/**
	 * The regular expression used to identify if the DNA sequence contains
	 * characters different of A, T, C and G.
	 */
	private static final String DNA_PATTERN = "[^A|a|T|t|C|c|G|g]";
	
	/**
	 * The regular expression used to identify a mutant DNA.
	 */
	private static final String MUTANT_DNA = "/|AAAA|CCCC|TTTT|GGGG|/";
	
	/**
	 * Return code used when DNA sequence is null.
	 */
	public static final String RC_DNA_NULL = "DNA sequence can not be null";
	
	/**
	 * Return code used when DNA sequence is empty.
	 */
	public static final String RC_DNA_EMPTY = "DNA sequence can not be empty";
	
	/**
	 * Return code used when DNA sequence does not have the same length as
	 * the nitrogenous bases.
	 */
	public static final String RC_DNA_FORMAT = "DNA chain must have the same lenght of nitrogenous bases.";
	
	/**
	 * Return code used when the nitrogenous bases are not in the same length.
	 */
	public static final String RC_NB_LENGTH = "DNA must contain nitrogenous bases with same length.";
	
	/**
	 * Return code used when the nitrogenous bases contains invalid characters.
	 */
	public static final String RC_NB_INVALID =  "Nitrogenous bases should be composed by A, T, C, G bases only.";
	
	/**
	 * This method injects the <code>DNA</code> into the service.
	 * 
	 * @param dna the <code>DNA</code> to be injected.
	 */
	@Autowired
	public void setDNA(DNA dna) {
        this.dna = dna;
    }
	
	@Autowired
    private HumanRepository humanRepository;
	
	/**
	 * This method registers the DNA into database.
	 */
	public long save(Human human) {
		long a = humanRepository.save(human).getId();
		return a;
	}
	
	public List<Human> findAll() {
		return humanRepository.findAll();
	}
	
	/**
	 * This method replace the nitrogenous bases with numbers in order
	 * to create a code, which will be the DNA identifier.
	 */
	public long createDnaId() {
		return dna.getDnaSequenceString().hashCode();
	}
	
	/**
	 * This method verifies if the DNA is from a mutant or not.
	 * 
	 * Initially it validates the input and then check if the DNA sequence
	 * belongs to a mutant or not.
	 * 
	 * @param dnaSequence the DNA sequence to be validated.
	 * @return <code>true</code> if user is a mutant, otherwise
	 *     <code>false</code>.
	 *     
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	public boolean isMutant(String[] dnaSequence) throws InvalidDnaException, InvalidNitrogenousBasesException {
		dna.setDnaSequence(dnaSequence);
		validateDna();
		validateNitrogenousBases();
		
		// if DNA sequence is smaller than 4, is not a mutant.
		if(dnaSequence[0].length() < 4) {
			return false;
		}
		
		createDnaMatrix();
		
		return isMutantDna();
	}
	
	/**
	 * This method verifies if the DNA sequence is valid.
	 * 
	 * Initially it validate if the DNA is not null, empty and in correct
	 * format. If one of the rules are not match, an
	 * <code>InvalidDnaException</code> is thrown.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 */
	public void validateDna() throws InvalidDnaException {
		if(null == dna.getDnaSequence()) {
			throw new InvalidDnaException(RC_DNA_NULL);
		}
		
		if(dna.getDnaSequence().length == 0) {
			throw new InvalidDnaException(RC_DNA_EMPTY);
		}
		
		if(dna.getDnaSequence().length != dna.getDnaSequence()[0].length()) {
			throw new InvalidDnaException(RC_DNA_FORMAT);
		}
	}
	
	/**
	 * This method verifies if the given nitrogenous bases are valid.
	 * 
	 * Initially it validate if all the nitrogenous bases contains the same
	 * length. If any entry contains a length different from the others, an
	 * <code>InvalidNitrogenousBasesException</code> is thrown.
	 * 
	 * Next, another validation is performed to check if the nitrogenous bases
	 * are composed of valid identifiers. If any base contains a character
	 * different of A, T, C or G, an
	 * <code>InvalidNitrogenousBasesException</code> is thrown.
	 * 
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G. 
	 */
	public void validateNitrogenousBases() throws InvalidNitrogenousBasesException {
		dna.setNitrogenousBasesSize(dna.getDnaSequence()[0].length());
		StringBuilder dnaBases = new StringBuilder();
		
		// iterate over DNA sequence to verify length and append to string for further use
		for(int i = 0; i < dna.getDnaSequence().length; i++) {
			if(dna.getDnaSequence()[i].length() != dna.getNitrogenousBasesSize()) {
				throw new InvalidNitrogenousBasesException(RC_NB_LENGTH);
			}
			dnaBases.append(dna.getDnaSequence()[i]);
		}
		
		dna.setDnaSequenceString(dnaBases.toString());
		
		// if regular expression find any occurrence, it means the DNA contains invalid characters and is not valid. 
		Pattern pattern = Pattern.compile(DNA_PATTERN);
		Matcher matcher = pattern.matcher(dnaBases.toString());
		if(matcher.find()) {
			throw new InvalidNitrogenousBasesException(RC_NB_INVALID);
		}
	}
	
	/**
	 * This method reads the given DNA sequence and store it into
	 * <code>char</code> arrays.
	 */
	private void createDnaMatrix()
	{
		char[][] matrixLeftToRight = new char[dna.getDnaSequence().length][dna.getNitrogenousBasesSize()];
		char[][] matrixRightToLeft = new char[dna.getDnaSequence().length][dna.getNitrogenousBasesSize()];
		int k = dna.getNitrogenousBasesSize() - 1;
		for(int i = 0; i < dna.getDnaSequence().length; i++) {
			for(int j = 0; j < dna.getNitrogenousBasesSize(); j++) {
				matrixLeftToRight[i][j] = dna.getDnaSequence()[i].charAt(j);
				matrixRightToLeft[i][j] = dna.getDnaSequence()[i].charAt(k);
				k--;
			}
			k = dna.getNitrogenousBasesSize() - 1;
			
		}
		dna.setDnaMatrixLeftToRight(matrixLeftToRight);
		dna.setDnaMatrixRightToLeft(matrixRightToLeft);
	}
	
	/**
	 * This method check all possible directions to find if a mutant DNA
	 * is present in the DNA sequence.
	 * 
	 * @return <code>true</code> if the DNA sequence contains at least four
	 * sequential characters of A, T, C or G, otherwise <code>false</code>. 
	 */
	private boolean isMutantDna() {
		// check it first to avoid useless processing
		if(dna.getDnaSequence()[0].length() < 4) {
			return false;
		}
		
		if(
				isMutantDnaFoundHorizontally()
				|| 
				isMutantDnaFoundVertically() 
				||
				isMutantFoundDiagonally(dna.getDnaMatrixLeftToRight())
				||
				isMutantFoundDiagonally(dna.getDnaMatrixRightToLeft())
				) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method applies a regular expression to the DNA sequences to
	 * identify if the DNA is from a mutant or not.
	 * 
	 * A mutant DNA contains at least four sequential characters A, T, C or G.
	 * 
	 * @param dnaSequence the DNA sequence to be verified.
	 * @return <code>true</code> if the DNA sequence contains at least four
	 * sequential characters of A, T, C or G, otherwise <code>false</code>.
	 */
	public boolean applyRegexToDna(String[] dnaSequence)
	{
		Pattern pattern = Pattern.compile(MUTANT_DNA, Pattern.CASE_INSENSITIVE);
		
		for(String dna : dnaSequence) {
			Matcher matcher = pattern.matcher(dna);
			if(matcher.find()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method verifies if a mutant DNA can be found when looking
	 * horizontally.
	 * 
	 * @return <code>true</code> if the DNA sequence contains at least four
	 * sequential characters of A, T, C or G, otherwise <code>false</code>.
	 */
	private boolean isMutantDnaFoundHorizontally() {
		return applyRegexToDna(dna.getDnaSequence());
	}
	
	/**
	 * This method verifies if a mutant DNA can be found when looking
	 * vertically.
	 * 
	 * @return <code>true</code> if the DNA sequence contains at least four
	 *     sequential characters of A, T, C or G, otherwise <code>false</code>.
	 */
	private boolean isMutantDnaFoundVertically() {
		String[] matrix = new String[dna.getNitrogenousBasesSize()];
		
		for(int i = 0; i < dna.getDnaSequence().length; i++) {
			StringBuilder base = new StringBuilder();
			for(int j = 0; j < dna.getDnaSequence()[i].length(); j++) {
				base.append(String.valueOf(dna.getDnaMatrixLeftToRight()[j][i]));
			}
			matrix[i] = base.toString();
		}
		
		return applyRegexToDna(matrix);
	}
	
	/**
	 * This method verifies if a mutant DNA can be found when looking
	 * diagonally.
	 * 
	 * First it calculate the number of diagonals that exists and have more
	 * than four characters.
	 * 
	 * Next it uses the DNA sequence to build the possible diagonals and
	 * check if the DNA belongs to a mutant or not.
	 * 
	 * @param dnaMatrix the DNA matrix used to search the DNA sequence.
	 * @return <code>true</code> if the DNA sequence contains at least four
	 *     sequential characters of A, T, C or G, otherwise <code>false</code>.
	 */
	private boolean isMutantFoundDiagonally(char[][] dnaMatrix) {
		
		/*
		 * Number of diagonals is the number of lines + numbers of rows of
		 * the matrix - 1. However, the first and last three diagonals does not
		 * have at least four characters, that's the number of valid diagonals
		 * is subtracted by 6.
		 * 
		 * diagonals = number of lines + number of rows -  1 - 6
		 */
		int diagonals = (dna.getDnaSequence().length + dna.getNitrogenousBasesSize()) - 7;
		int index = 0;
		int line = dna.getDnaSequence().length - 1;
		int column = 0;
		String[] matrix = new String[diagonals];
		
		for(int i = 3; i <= line; i++)  {
			StringBuilder base = new StringBuilder();
			line = i;
			for(int j = 0; j < dna.getNitrogenousBasesSize(); j++) {
				base.append(String.valueOf(dnaMatrix[line][column]));
				line--;
				column++;
				
				if(line == -1) {
					column = 0;
					line = dna.getDnaSequence().length - 1;
					break;
				}
			}
			matrix[index] = base.toString();
			index++;
		}
		
		line = dna.getDnaSequence().length -1;
		int missingDiagonals = diagonals-index;
		for(int j = 1; j <= missingDiagonals; j++) {
			StringBuilder base = new StringBuilder();
			column = j;
			while( j <= dna.getNitrogenousBasesSize()) {
				base.append(String.valueOf(dnaMatrix[line][column]));
				line--;
				column++;
				
				if(column == dna.getNitrogenousBasesSize()) {
					line = dna.getDnaSequence().length - 1;
					break;
				}
			}
			matrix[index] = base.toString();
			index++;
		}
		
		return applyRegexToDna(matrix);
	}

}
