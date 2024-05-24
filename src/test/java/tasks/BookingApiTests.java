package tasks;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import question.LastResponse;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class BookingApiTests {

    private Actor anna;
    private String token;
    private int bookingId1;
    private int bookingId2;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        anna = Actor.named("Anna").whoCan(CallAnApi.at("https://restful-booker.herokuapp.com"));
    }

    @Test
    public void bookingFlowTest() {
        // Step 1: Create Token
        givenThat(anna).wasAbleTo(CreateToken.withCredentials("admin", "password123"));

        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));

        token = LastResponse.field("token").answeredBy(anna);

        // Step 2: Create Bookings
        anna.attemptsTo(CreateBooking.withDetails("Pedro", "Gutierrez", 100, true, "2024-03-01", "2024-04-01", "Comics"));
        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));
        then(anna).should(seeThat(LastResponse.field("booking.firstname"), is("Pedro")));
        bookingId1 = Integer.parseInt(LastResponse.field("bookingid").answeredBy(anna));

        anna.attemptsTo(CreateBooking.withDetails("Javier", "Jaramillo", 356, true, "2024-03-15", "2024-04-15", "Terror"));
        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));
        then(anna).should(seeThat(LastResponse.field("booking.firstname"), is("Javier")));
        bookingId2 = Integer.parseInt(LastResponse.field("bookingid").answeredBy(anna));

        // Step 3: Get Bookings
        anna.attemptsTo(GetBooking.byId(bookingId1));
        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));
        then(anna).should(seeThat(LastResponse.field("firstname"), is("Pedro")));

        anna.attemptsTo(GetBooking.byId(bookingId2));
        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));
        then(anna).should(seeThat(LastResponse.field("firstname"), is("Javier")));

        // Step 4: Update Bookings
        anna.attemptsTo(UpdateBooking.withDetails(bookingId1, "Jose", "Gutierrez", 100, true, "2023-05-12", "2023-06-28", "Comics", token));
        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));
        then(anna).should(seeThat(LastResponse.field("firstname"), is("Jose")));

        anna.attemptsTo(UpdateBooking.withDetails(bookingId2, "Javier", "Mora", 356, true, "2023-06-20", "2023-07-20", "Terror", token));
        then(anna).should(seeThat(LastResponse.statusCode(), is(200)));
        then(anna).should(seeThat(LastResponse.field("firstname"), is("Javier")));

        // Step 5: Delete Bookings
        anna.attemptsTo(DeleteBooking.byId(bookingId1, token));
        then(anna).should(seeThat(LastResponse.statusCode(), is(201)));

        anna.attemptsTo(DeleteBooking.byId(bookingId2, token));
        then(anna).should(seeThat(LastResponse.statusCode(), is(201)));
    }
}


