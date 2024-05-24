package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.thucydides.core.annotations.Step;

public class DeleteBooking implements Task {

    private final int bookingId;
    private final String token;

    public DeleteBooking(int bookingId, String token) {
        this.bookingId = bookingId;
        this.token = token;
    }

    @Step("{0} deletes the booking with id #bookingId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/booking/{id}")
                        .with(request -> request
                                .pathParam("id", bookingId)
                                .header("Cookie", "token=" + token)
                        )
        );
    }

    public static DeleteBooking byId(int bookingId, String token) {
        return new DeleteBooking(bookingId, token);
    }
}


