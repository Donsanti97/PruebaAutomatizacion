package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteBooking implements Task {

    private final int bookingId;
    private final String token;

    public DeleteBooking(int bookingId, String token) {
        this.bookingId = bookingId;
        this.token = token;
    }

    @Step("{0} deletes the booking for booking id #bookingId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/booking/{id}")
                        .with(request -> request
                                .header("Cookie", "token=" + token)
                                .pathParam("id", bookingId)
                        )
        );
    }

    public static DeleteBooking byId(int bookingId, String token) {
        return instrumented(DeleteBooking.class, bookingId, token);
    }
}



