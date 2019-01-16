package mutant.domain;

import org.springframework.stereotype.Component;

/**
 * This class describes the DNA attributes.
 * 
 * @author Hélio De Rosa Junior
 */
@Component
public class DNA {

	/**
	 * The number of characters of the nitrogenous base.
	 */
	private int nitrogenousBasesSize;
	
	/**
	 * The matrix that holds the DNA nitrogenous bases read from left to right
	 * that will be used to identify if human is a mutant or not.
	 */
	private char[][] dnaMatrixLeftToRight;
	
	/**
	 * The matrix that holds the DNA nitrogenous bases read from right to left
	 * that will be used to identify if human is a mutant or not.
	 */
	private char[][] dnaMatrixRightToLeft;
	
	/**
	 * The array that holds the DNA nitrogenous bases that will be used to
	 * identify if human is a mutant or not.
	 */
	private String[] dnaSequence;
	
	/**
	 * The string that holds the DNA nitrogenous bases.
	 */
	private String dnaSequenceString;
	
	/**
	 * This method gets the number of characters of the nitrogenous base.
	 * 
	 * @return the number of characters of the nitrogenous base.
	 */
	public int getNitrogenousBasesSize() {
		return nitrogenousBasesSize;
	}
	
	/**
	 * This method sets the number of characters of the nitrogenous base.
	 * 
	 * @param nitrogenousBasesSize the number of characters of the nitrogenous
	 *     base.
	 */
	public void setNitrogenousBasesSize(int nitrogenousBasesSize) {
		this.nitrogenousBasesSize = nitrogenousBasesSize;
	}
	
	/**
	 * This method gets the DNA matrix (read from left to right) used to
	 * validate if the human is a mutant or not.
	 * 
	 * @return the loaded DNA matrix read from left to right.
	 */
	public char[][] getDnaMatrixLeftToRight() {
		return dnaMatrixLeftToRight;
	}
	
	/**
	 * This method sets the DNA matrix (read from left to right) used to
	 * validate if the human is a mutant or not.
	 * 
	 * @param dnaMatrixLeftToRight the DNA matrix read from left to right.
	 */
	public void setDnaMatrixLeftToRight(char[][] dnaMatrixLeftToRight) {
		this.dnaMatrixLeftToRight = dnaMatrixLeftToRight;
	}
	
	/**
	 * This method gets the DNA matrix (read from right to left) used to
	 * validate if the human is a mutant or not.
	 * 
	 * @return the loaded DNA matrix read from right to left.
	 */
	public char[][] getDnaMatrixRightToLeft() {
		return dnaMatrixRightToLeft;
	}
	
	/**
	 * This method sets the DNA matrix  (read from right to left) used to
	 * validate if the human is a mutant or not.
	 * 
	 * @param dnaMatrixRightToLeft the DNA matrix read from right to left.
	 */
	public void setDnaMatrixRightToLeft(char[][] dnaMatrixRightToLeft) {
		this.dnaMatrixRightToLeft = dnaMatrixRightToLeft;
	}

	/**
	 * This method gets the DNA sequence used to validate if the human is a
	 * mutant or not.
	 * 
	 * @return the loaded DNA sequence.
	 */
	public String[] getDnaSequence() {
		return dnaSequence;
	}
	
	/**
	 * This method sets the DNA sequence used to validate if the human is a
	 * mutant or not.
	 * 
	 * @param dnaSequence the DNA sequence.
	 */
	public void setDnaSequence(String[] dnaSequence) {
		this.dnaSequence = dnaSequence;
	}
	
	/**
	 * This method gets the DNA sequence.
	 * 
	 * @return the loaded DNA sequence.
	 */
	public String getDnaSequenceString() {
		return dnaSequenceString;
	}
	
	/**
	 * This method sets the DNA sequence.
	 * 
	 * @param dnaSequenceString the DNA sequence.
	 */
	public void setDnaSequenceString(String dnaSequenceString) {
		this.dnaSequenceString = dnaSequenceString;
	}
}
