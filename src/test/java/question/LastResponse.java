package question;

/*import io.restassured.response.Response;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.TheResponse;
//import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class LastResponse {
    public static Question<Integer> statusCode() {
        return actor -> TheResponse.statusCode().answeredBy(actor);
    }

    public static Question<String> field(String fieldName) {
        return actor -> net.serenitybdd.screenplay.rest.questions.LastResponse.received().answeredBy(actor).jsonPath().getString(fieldName);
    }

    public static Question<Response> body() {
        return actor -> net.serenitybdd.screenplay.rest.questions.LastResponse.received().answeredBy(actor)*//*TheResponse.received().answeredBy(actor)*//*;
    }
}*/
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import net.serenitybdd.screenplay.rest.questions.RestQueryFunction;

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


