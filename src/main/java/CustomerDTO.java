import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDTO {
    private String body;

    public CustomerDTO (String body) {
        this.body = body;
    } 

    public Customer getCustomer() throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode customerJson = mapper.readTree(body).get("customer");
        return new Customer(
            customerJson.get("name").asText(),
            customerJson.get("location").asText(),
            customerJson.get("age").asInt(),            
            customerJson.get("income").asInt()            
        );
    }

}