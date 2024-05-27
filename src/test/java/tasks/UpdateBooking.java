package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.thucydides.core.annotations.Step;
import io.restassured.http.ContentType;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateBooking implements Task {

    private final int bookingId;
    private final String firstname;
    private final String lastname;
    private final String token;

    public UpdateBooking(int bookingId, String firstname, String lastname, String token) {
        this.bookingId = bookingId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.token = token;
    }

    @Step("{0} updates the booking for booking id #bookingId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/booking/{id}")
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .header("Cookie", "token=" + token)
                                .pathParam("id", bookingId)
                                .body("{\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\"}")
                        )
        );
    }

    public static UpdateBooking withDetails(int bookingId, String firstname, String lastname, String token) {
        return instrumented(UpdateBooking.class, bookingId, firstname, lastname, token);
    }
}


//.body("{\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\",\"totalprice\":" + totalprice + ",\"depositpaid\":" + depositpaid + ",\"bookingdates\":{\"checkin\":\"" + checkin + "\",\"checkout\":\"" + checkout + "\"},\"additionalneeds\":\"" + additionalneeds + "\"}")


