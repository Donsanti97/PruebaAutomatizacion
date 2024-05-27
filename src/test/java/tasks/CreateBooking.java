package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.core.annotations.Step;
import io.restassured.http.ContentType;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateBooking implements Task {

    private final String firstname;
    private final String lastname;

    public CreateBooking(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Step("{0} creates a new booking for #firstname #lastname")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/booking")
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .body("{\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\"}")
                        )
        );
    }

    public static CreateBooking withDetails(String firstname, String lastname) {
        return instrumented(CreateBooking.class, firstname, lastname);
    }
}



