package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;
import io.restassured.http.ContentType;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateToken implements Task {

    private final String username;
    private final String password;

    public CreateToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Step("{0} requests an authentication token")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/auth")
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                        )
        );
    }

    public static CreateToken withCredentials(String username, String password) {
        return instrumented(CreateToken.class, username, password);
    }
}



