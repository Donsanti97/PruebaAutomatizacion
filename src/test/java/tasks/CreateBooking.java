package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Step;
import io.restassured.http.ContentType;

public class CreateBooking implements Task {

    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final String checkin;
    private final String checkout;
    private final String additionalneeds;

    public CreateBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.additionalneeds = additionalneeds;
    }

    @Step("{0} creates a new booking for #firstname #lastname")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/booking")
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .body("{\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\",\"totalprice\":\"" + totalprice + "\",\"depositpaid\":\"" + depositpaid + "\",\"bookingdates\":{\"checkin\":\"" + checkin + "\",\"checkout\":\"" + checkout + "\"},\"additionalneeds\":\"" + additionalneeds + "\"}")
                        )
        );
    }

    public static CreateBooking withDetails(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        return new CreateBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
    }
}


