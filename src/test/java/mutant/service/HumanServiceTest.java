package mutant.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import mutant.domain.DNA;
import mutant.exception.InvalidDnaException;
import mutant.exception.InvalidNitrogenousBasesException;
import mutant.service.impl.HumanServiceImpl;

public class HumanServiceTest {
	
	private HumanServiceImpl humanService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Before
    public void setUp() {
		MockitoAnnotations.initMocks(this);
		humanService = new HumanServiceImpl();
		humanService.setDNA(new DNA());
    }
	
	/**
	 * This test ensures that a <code>InvalidDnaExpcetion</code> is thrown
	 * when a null DNA sequence is given.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNullDna() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidDnaException.class, HumanServiceImpl.RC_DNA_NULL);
		humanService.isMutant(null);
	}
	
	/**
	 * This test ensures that a <code>InvalidDnaExpcetion</code> is thrown
	 * when an empty DNA sequence is given.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testEmptyDna() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidDnaException.class, HumanServiceImpl.RC_DNA_EMPTY);
		humanService.isMutant(new String[] {});
	}
	
	/**
	 * This test ensures that a <code>InvalidDnaExpcetion</code> is thrown
	 * when a DNA sequence with wrong format is given.
	 * 
	 * As input, a DNA with 3 sequences of 4 nitrogenous bases. An error
	 * should be thrown because the number of sequences must be the same
	 * as the number of nitrogenous bases.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testDnaWithInvalidFormat_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidDnaException.class, HumanServiceImpl.RC_DNA_FORMAT);
		humanService.isMutant(new String[] {"ATGC", "CAGC", "TTAT"});
	}
	
	/**
	 * This test ensures that a <code>InvalidDnaExpcetion</code> is thrown
	 * when a DNA sequence with wrong format is given.
	 * 
	 * As input, a DNA with 4 sequences of 5 nitrogenous bases. An error
	 * should be thrown because the number of sequences must be the same
	 * as the number of nitrogenous bases.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testDnaWithInvalidFormat_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidDnaException.class, HumanServiceImpl.RC_DNA_FORMAT);
		humanService.isMutant(new String[] {"ATGCC", "CTAGC", "TTATA", "CTGAT"});
	}
	
	/**
	 * This test ensures that a <code>InvalidDnaExpcetion</code> is thrown
	 * when a DNA sequence with wrong format is given.
	 * 
	 * As input, a DNA with 2 sequences of 5 nitrogenous bases. An error
	 * should be thrown because the number of sequences must be the same
	 * as the number of nitrogenous bases.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testDnaWithInvalidFormat_3() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidDnaException.class, HumanServiceImpl.RC_DNA_FORMAT);
		humanService.isMutant(new String[] {"ATGCC", "CTAGC"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases length is
	 * given.
	 * 
	 * As input, a DNA with 3 sequences of 4 nitrogenous bases, and 1 sequence
	 * with 5 nitrogenous bases. An error should be thrown because the number
	 * of nitrogenous bases should be the same.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBLength_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_LENGTH);
		humanService.isMutant(new String[] {"ATGC", "CTAC", "TATA", "CTGTA"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases length is
	 * given.
	 * 
	 * As input, a DNA with 4 sequences of 5 nitrogenous bases, and 1 sequence
	 * with 6 nitrogenous bases. An error should be thrown because the number
	 * of nitrogenous bases should be the same.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBLength_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_LENGTH);
		humanService.isMutant(new String[] {"ATGCA", "CTACT", "TATAC", "CTGTA", "CTGTAAC"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases length is
	 * given.
	 * 
	 * As input, a DNA with 5 sequences of 6 nitrogenous bases, and 1 sequence
	 * with 7 nitrogenous bases. An error should be thrown because the number
	 * of nitrogenous bases should be the same.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBLength_3() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_LENGTH);
		humanService.isMutant(new String[] {"CATGCA", "CTAGCT", "CTATAC", "CATGTA", "CTGTAA", "ATGTTAA"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases values is
	 * given.
	 * 
	 * As input, a DNA sequence with valid format is given, but with letter
	 * characters. An error should be thrown because it must accept only A, T,
	 * C, or G characters.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBInvalid_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_INVALID);
		humanService.isMutant(new String[] {"CABGCA", "CTAGCT", "CTATAC", "CATGTA", "CTGTAA", "ATGTTA"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases values is
	 * given.
	 * 
	 * As input, a DNA sequence with valid format is given, but with special
	 * characters. An error should be thrown because it must accept only A, T,
	 * C, or G characters.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBInvalid_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_INVALID);
		humanService.isMutant(new String[] {"CAT@CA", "&TAGCT", "CTATA!", "C'TGTA", "CT$TAA", "ATGT*A"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases values is
	 * given.
	 * 
	 * As input, a DNA sequence with valid format is given, but with spaces.
	 * An error should be thrown because it must accept only A, T, C, or G
	 * characters.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBInvalid_3() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_INVALID);
		humanService.isMutant(new String[] {"CATGCA", "C C = ", "CTA&AC", "CATGTA", "CT%TAA", "ATGTTA"});
	}
	
	/**
	 * This test ensures that a <code>InvalidNitrogenousBasesException</code>
	 * is thrown when a DNA sequence with invalid nitrogenous bases values is
	 * given.
	 * 
	 * As input, a DNA sequence with valid format is given, but with numbers.
	 * An error should be thrown because it must accept only A, T, C, or G
	 * characters.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testNBInvalid_4() throws InvalidDnaException, InvalidNitrogenousBasesException {
		prepareException(InvalidNitrogenousBasesException.class, HumanServiceImpl.RC_NB_INVALID);
		humanService.isMutant(new String[] {"C2T9CA", "C C = ", "CT1&AC", "CA674A", "CT%TAA", "AT3TT7"});
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is not
	 * from a mutant when 1 sequence of 1 nitrogenous base is given.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_1x1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertFalse(humanService.isMutant(new String[] {"A"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is not
	 * from a mutant when 2 sequences of 2 nitrogenous bases is given.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_2x2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertFalse(humanService.isMutant(new String[] {"AC", "TG"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is not
	 * from a mutant when 3 sequences of 3 nitrogenous bases is given.
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_3x3() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertFalse(humanService.isMutant(new String[] {"ACT", "TGA", "GAC"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is not
	 * from a mutant when 4 sequences of 4 nitrogenous bases is given.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A</pre>
     * <pre>    T  G  A  C</pre>
     * <pre>    G  A  C  T</pre>
     * <pre>    C  T  G  A</pre>
     * <br>
     * <b>Expected result:</b> <code>false</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_4x4_False() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertFalse(humanService.isMutant(new String[] {"ACTA", "TGAC", "GACT", "CTGA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 4 sequences of 4 nitrogenous bases is given, and the
	 * characters are located horizontally.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A</pre>
     * <pre>    T  G  A  C</pre>
     * <pre>    G  G  G  G</pre>
     * <pre>    T  T  G  G</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_4x4_True_Horizontally() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTA", "TGAC", "GGGG", "TTGG"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 4 sequences of 4 nitrogenous bases is given, and the
	 * characters are located vertically.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A</pre>
     * <pre>    T  C  A  C</pre>
     * <pre>    G  C  G  G</pre>
     * <pre>    T  C  G  G</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_4x4_True_Vertically() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTA", "TCAC", "GCGG", "TCGG"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 4 sequences of 4 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A</pre>
     * <pre>    T  G  A  C</pre>
     * <pre>    G  A  C  T</pre>
     * <pre>    A  T  G  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_4x4_True_LeftDiagonal() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTA", "TGAC", "GACT", "ATGA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 4 sequences of 4 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    G  C  T  A</pre>
     * <pre>    T  G  A  C</pre>
     * <pre>    G  A  G  T</pre>
     * <pre>    T  T  G  G</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_4x4_True_RightDiagonal() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"GCTA", "TGAC", "GAGT", "TTGG"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is not
	 * from a mutant when 5 sequences of 5 nitrogenous bases is given.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  T</pre>
     * <pre>    T  G  A  C  A</pre>
     * <pre>    G  A  C  T  G</pre>
     * <pre>    C  T  G  A  T</pre>
     * <pre>    A  T  C  G  T</pre>
     * <br>
     * <b>Expected result:</b> <code>false</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_False() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertFalse(humanService.isMutant(new String[] {"ACTAT", "TGACA", "GACTG", "CTGAT", "ATCGT"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located horizontally.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    G  G  G  G  C</pre>
     * <pre>    T  T  G  G  A</pre>
     * <pre>    A  C  A  G  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_Horizontally_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TGACG", "GGGGC", "TTGGA", "ACAGA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located horizontally.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    C  G  G  G  G</pre>
     * <pre>    T  T  G  G  A</pre>
     * <pre>    A  C  A  G  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_Horizontally_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TGACG", "CGGGG", "TTGGA", "ACAGA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located vertically.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  G  A  G  G</pre>
     * <pre>    C  A  G  G  G</pre>
     * <pre>    T  T  G  G  A</pre>
     * <pre>    A  C  A  G  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_Vertically_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TGAGG", "CAGGG", "TTGGA", "ACAGA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located vertically.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  G  A</pre>
     * <pre>    T  G  A  G  G</pre>
     * <pre>    C  A  G  G  G</pre>
     * <pre>    T  T  G  G  A</pre>
     * <pre>    A  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_Vertically_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTGA", "TGAGG", "CAGGG", "TTGGA", "ACATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    C  A  G  G  G</pre>
     * <pre>    A  T  G  G  A</pre>
     * <pre>    A  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_LeftDiagonal_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TGACG", "CAGGG", "ATGGA", "ACATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  C</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    C  C  C  G  G</pre>
     * <pre>    A  C  G  G  A</pre>
     * <pre>    C  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_LeftDiagonal_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAC", "TGACG", "CCCGG", "ACGGA", "CCATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    C  C  C  G  G</pre>
     * <pre>    A  C  G  G  A</pre>
     * <pre>    C  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_LeftDiagonal_3() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TGACG", "CCCGG", "ACGGA", "CCATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  C</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    C  C  C  G  G</pre>
     * <pre>    A  C  G  G  A</pre>
     * <pre>    G  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_LeftDiagonal_4() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAC", "TGACG", "CCCGG", "ACGGA", "GCATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  C</pre>
     * <pre>    T  G  A  C  G</pre>
     * <pre>    C  C  T  G  G</pre>
     * <pre>    A  C  G  G  A</pre>
     * <pre>    G  G  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_LeftDiagonal_5() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAC", "TGACG", "CCTGG", "ACGGA", "GGATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  G  C  C  G</pre>
     * <pre>    C  A  G  C  G</pre>
     * <pre>    A  T  G  G  C</pre>
     * <pre>    A  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_RightDiagonal_1() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TGCCG", "CAGCG", "ATGGC", "ACATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    C  C  T  A  A</pre>
     * <pre>    T  C  A  C  G</pre>
     * <pre>    C  A  C  G  G</pre>
     * <pre>    A  T  G  C  C</pre>
     * <pre>    A  C  A  T  C</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_RightDiagonal_2() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"CCTAA", "TCACG", "CACGG", "ATGCC", "ACATC"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    C  C  T  A  A</pre>
     * <pre>    T  C  A  C  G</pre>
     * <pre>    C  A  C  G  G</pre>
     * <pre>    A  T  G  C  C</pre>
     * <pre>    A  C  A  T  A</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_RightDiagonal_3() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"CCTAA", "TCACG", "CACGG", "ATGCC", "ACATA"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  C  A  C  G</pre>
     * <pre>    C  A  C  G  G</pre>
     * <pre>    A  T  G  C  C</pre>
     * <pre>    A  C  A  T  C</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_RightDiagonal_4() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TCACG", "CACGG", "ATGCC", "ACATC"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located in the diagonal.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A</pre>
     * <pre>    T  C  A  C  G</pre>
     * <pre>    C  T  G  G  G</pre>
     * <pre>    A  T  T  C  C</pre>
     * <pre>    A  C  A  T  C</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_5x5_True_RightDiagonal_5() throws InvalidDnaException, InvalidNitrogenousBasesException {
		assertTrue(humanService.isMutant(new String[] {"ACTAA", "TCACG", "CTGGG", "ATTCC", "ACATC"}));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located horizontally.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A  T  A  G  C  A</pre>
     * <pre>    T  G  A  C  T  C  C  G  T  T</pre>
     * <pre>    G  A  T  A  C  T  C  G  G  C</pre>
     * <pre>    T  T  G  G  A  A  G  T  C  A</pre>
     * <pre>    A  T  A  G  A  T  C  A  G  C</pre>
     * <pre>    T  G  C  C  T  G  C  A  T  T</pre>
     * <pre>    T  T  A  C  G  G  A  C  C  A</pre>
     * <pre>    A  C  A  T  C  G  A  G  C  A</pre>
     * <pre>    T  G  A  C  A  T  C  A  T  G</pre>
     * <pre>    A  C  T  A  C  C  T  A  G  C</pre>
     * <br>
     * <b>Expected result:</b> <code>false</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_10x10_False() throws InvalidDnaException, InvalidNitrogenousBasesException {
		String[] dna = new String[] {"ACTAATAGCA", "TGACTCCGTT", "GATACTCGGC", "TTGGAAGTCA", 
				"ATAGATCAGC", "TGCCTGCATT", "TTACGGACCA", "ACATCGAGCA", "TGACATCATG", "ACTACCTAGC"};
		assertFalse(humanService.isMutant(dna));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 10 sequences of 10 nitrogenous bases is given, and the
	 * characters are located horizontally.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A  T  A  G  C  A</pre>
     * <pre>    T  G  A  C  C  C  C  G  T  T</pre>
     * <pre>    G  A  T  A  C  T  C  G  G  C</pre>
     * <pre>    T  T  G  G  A  A  G  T  C  A</pre>
     * <pre>    A  T  A  G  A  T  C  A  G  C</pre>
     * <pre>    T  G  C  C  T  G  C  A  T  T</pre>
     * <pre>    T  T  A  C  G  G  A  C  C  A</pre>
     * <pre>    A  C  A  T  C  G  A  G  C  A</pre>
     * <pre>    T  G  A  C  A  T  C  A  T  G</pre>
     * <pre>    A  C  T  A  C  C  T  A  G  C</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_10x10_True_Horizontally() throws InvalidDnaException, InvalidNitrogenousBasesException {
		String[] dna = new String[] {"ACTAATAGCA", "TGACCCCGTT", "GATACTCGGC", "TTGGAAGTCA", 
				"ATAGATCAGC", "TGCCTGCATT", "TTACGGACCA", "ACATCGAGCA", "TGACATCATG", "ACTACCTAGC"};
		assertTrue(humanService.isMutant(dna));
	}
	
	/**
	 * This test ensures that a DNA sequence recognizes that a DNA is from a
	 * mutant when 5 sequences of 5 nitrogenous bases is given, and the
	 * characters are located horizontally.
	 * <br>
	 * <br>
	 * <b>Test input:</b>
     * <pre>    A  C  T  A  A  T  A  G  C  A</pre>
     * <pre>    T  G  A  C  T  C  C  G  T  T</pre>
     * <pre>    G  A  T  A  C  T  C  G  G  C</pre>
     * <pre>    T  T  G  G  A  A  G  T  C  A</pre>
     * <pre>    T  A  A  G  A  T  C  A  G  C</pre>
     * <pre>    T  G  C  C  T  G  C  A  T  T</pre>
     * <pre>    T  T  A  C  G  G  A  C  C  A</pre>
     * <pre>    A  C  A  T  C  G  A  G  C  A</pre>
     * <pre>    T  G  A  C  A  T  C  A  T  G</pre>
     * <pre>    A  C  T  A  C  C  T  A  G  C</pre>
     * <br>
     * <b>Expected result:</b> <code>true</code>
     * <br>
     * <br>
	 * 
	 * @throws InvalidDnaException an <code>InvalidDnaException</code> when the
	 *     DNA sequence is null.
	 * @throws InvalidNitrogenousBasesException an
	 *     <code>InvalidNitrogenousBasesException</code> is thrown when the
	 *     nitrogenous bases does not have the same length or when they are
	 *     composed of characters different from A, T, C or G.
	 */
	@Test
	public void testMutant_10x10_True_Vertically() throws InvalidDnaException, InvalidNitrogenousBasesException {
		String[] dna = new String[] {"ACTAATAGCA", "TGACTCCGTT", "GATACTCGGC", "TTGGAAGTCA", 
				"TAAGATCAGC", "TGCCTGCATT", "TTACGGACCA", "ACATCGAGCA", "TGACATCATG", "ACTACCTAGC"};
		assertTrue(humanService.isMutant(dna));
	}
	
	/**
     * This method set the expected exception of the test.
     *
     * @param className the exception type.
     * @param message the message to be displayed.
     */
	private void prepareException(Class<? extends Throwable> className, String message) {
		expectedException.expect(className);
	    expectedException.expectMessage(message);
	}
	
}
