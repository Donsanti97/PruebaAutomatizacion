package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;
import io.restassured.http.ContentType;

public class CreateToken implements Task {

    private final String username;
    private final String password;

    public CreateToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Step("{0} creates a token with #username and #password")
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
        return new CreateToken(username, password);
    }
}


