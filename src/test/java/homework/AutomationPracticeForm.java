package homework;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import data.Genders;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBaseExtended;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AutomationPracticeForm  extends TestBaseExtended {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    @Tag("remote")
    @Step("Проверка заполнения и отправки формы")
    void testRegistrationForm() {
        Faker faker = new Faker();


        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        Genders genders = faker.options().option(Genders.values());
        String userNumber = "0123456789";
        String birthMonth = "August";
        String birthYear = "1995";
        String userSubject = "Computer Science";
        String currentAddress = faker.address().streetAddress();
        String userHobbies = "Reading";
        String userState = "Uttar Pradesh";
        String userCity = "Agra";


        step("Открываем форму", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
        });

        step("Заполняем форму", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $("#genterWrapper").$(byText(genders.toString())).click();
            $("#userNumber").setValue(userNumber);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(birthMonth);
            $(".react-datepicker__year-select").selectOption(birthYear);
            $(".react-datepicker__day--031:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue(userSubject).pressEnter();
            $("#uploadPicture").uploadFromClasspath("picture.jpg");
            $("#currentAddress").setValue(currentAddress);
            $("#hobbiesWrapper").$(byText(userHobbies)).click();
            $("#state").click();
            $(byText(userState)).click();
            $("#city").click();
            $(byText(userCity)).click();
            $("#submit").pressEnter();
        });

        step("Проверка заполнения формы", () -> {
            $(".modal-header").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text(firstName), text(lastName),
                    text(userEmail), text(userNumber));
        });

    }
}
