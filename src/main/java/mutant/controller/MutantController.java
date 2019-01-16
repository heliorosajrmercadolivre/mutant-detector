package mutant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mutant.domain.Human;
import mutant.exception.InvalidDnaException;
import mutant.exception.InvalidNitrogenousBasesException;
import mutant.service.HumanService;

/**
 * Mutant controller responsible to handle /mutant requests.
 * 
 * @author Hélio De Rosa Junior
 */
@RestController
public class MutantController {

	/**
	 * DNA constant used to get information from post data.
	 */
	private static final String DNA = "DNA";
	
	/**
	 * Mutant service class autowired to the controller, responsible to
	 * validate is user is a mutant or not.
	 */
	@Autowired
	private HumanService humanService;
	
    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public ResponseEntity<?> mutant(@RequestBody String bodyContent) {
		try {
			String[] dna = paseDnaFromJson(bodyContent);
			if(humanService.isMutant(dna)) {
				Human human = new Human();
				human.setId(humanService.createDnaId());
				human.setMutant(true);
				humanService.save(human);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				Human human = new Human();
				human.setId(humanService.createDnaId());
				human.setMutant(false);
				humanService.save(human);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
		} catch (InvalidDnaException | InvalidNitrogenousBasesException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
    }
    
    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces="application/json; charset=UTF-8")
    public ResponseEntity<?> stats() {
		List<Human> list = humanService.findAll();
		
		int countMutantDna = 0;
		for(Human h : list) {
			if(h.isMutant()) {
				countMutantDna++;
			}
		}
		float ratio = (float) (countMutantDna != 0 ? ((float)list.size()/countMutantDna) : 0);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode response = mapper.createObjectNode();
		response.put("count_mutant_dna", countMutantDna);
		response.put("count_human_dna", list.size());
		response.put("ratio", ratio);
        
		if(!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * This method parses the DNA received from post request and return it
     * as a <code>String</code> array for further processing.
     * 
     * @param json the JSON with DNA data to be parsed.
     * @return a <code>String</code> array with DNA parsed data.
     */
    public String[] paseDnaFromJson(String json) {
    	JacksonJsonParser parser = new JacksonJsonParser();
    	Map<String, Object> parsed = parser.parseMap(json.toUpperCase());

        if (!parsed.containsKey(DNA)) {
            throw new JsonParseException();
        }

        List<String> dnaSequence = (List<String>) parsed.get(DNA);
        return dnaSequence.toArray(new String[]{});
    }
}
