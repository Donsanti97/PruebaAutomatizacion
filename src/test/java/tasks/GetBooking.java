package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetBooking implements Task {

    private final int bookingId;

    public GetBooking(int bookingId) {
        this.bookingId = bookingId;
    }

    @Step("{0} fetches the booking details for booking id #bookingId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/booking/{id}").with(request -> request.pathParam("id", bookingId))
        );
    }

    public static GetBooking byId(int bookingId) {
        return instrumented(GetBooking.class, bookingId);
    }
}



