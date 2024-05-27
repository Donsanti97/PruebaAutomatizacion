package question;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Question;

public class LastResponse {

    public static Question<Integer> statusCode() {
        return actor -> SerenityRest.lastResponse().getStatusCode();
    }

    public static Question<String> field(String jsonPath) {
        return actor -> SerenityRest.lastResponse().jsonPath().getString(jsonPath);
    }

    public static Question<String> body() {
        return actor -> SerenityRest.lastResponse().getBody().asString();
    }
}




