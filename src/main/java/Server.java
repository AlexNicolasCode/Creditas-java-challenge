import io.javalin.Javalin;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

class Server {

    public static void main(String[] args) {
        Javalin.create(config -> {
                config.plugins.enableDevLogging();
            })
            .post("/", ctx -> {
                Customer customer = new CustomerDTO(ctx.body()).getCustomer();
                List<Loan> avaiableLoans = new LoanMatcher(customer).loans();
                ObjectNode response = buildResponse(customer.name(), avaiableLoans);
                ctx.json(response.toString());
            })
            .start(8080);
    }

    public static ObjectNode buildResponse(String customerName, List<Loan> avaiableLoans) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        response.put("customer", customerName);
        ArrayNode loans = response.putArray("loans");
        for (Loan loan : avaiableLoans) {
            loans.addObject()
                .put("type", loan.type())
                .put("taxes", loan.tax());
        }
        return response;
    }
}