package mutant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class describes the basic attributes of a human.
 * 
 * @author Hélio De Rosa Junior
 */
@Entity
@Table(name="human")
public class Human {

	/**
	 * The DNA identifier.
	 */
	@Id
    @Column(name = "id")
	private long id;
	
	/**
	 * The human DNA sequence.
	 */
	@Transient
	public String[] dnaSequence;
	
	/**
	 * Flag indicating if human is a mutant or not.
	 */
	@Column(name = "mutant_flag")
	public boolean isMutant;
	
	/**
	 * Gets the human DNA sequence.
	 * 
	 * @return the human DNA sequence.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Gets the human DNA sequence.
	 * 
	 * @return the human DNA sequence.
	 */
	public String[] getDnaSequence() {
		return dnaSequence;
	}
	
	/**
	 * Get the human's mutant status.
	 * 
	 * @return <code>true</code> if human is a mutant, <code>false</code>
	 *     otherwise.
	 */
	public boolean isMutant() {
		return isMutant;
	}
	
	/**
	 * Sets the human DNA id.
	 * 
	 * @return the human DNA id.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Sets the human DNA sequence.
	 * 
	 * @param dnaSequence the DNA sequence to set.
	 */
	public void setDnaSequence(String[] dnaSequence) {
		this.dnaSequence = dnaSequence;
	}
	
	/**
	 * Sets the human's mutant status.
	 * 
	 * @param isMutant the human's mutant status.
	 */
	public void setMutant(boolean isMutant) {
		this.isMutant = isMutant;
	}
}
