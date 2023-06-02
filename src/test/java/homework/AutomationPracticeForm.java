package homework;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    @DisplayName("Проверка заполнения и отправки формы")
    void testRegistrationForm() {
        String firstName = "John";
        String lastName = "Doe";
        String userEmail = "some-email@test.com";
        String userGender = "Male";
        String userNumber = "0123456789";
        String birthMonth = "August";
        String birthYear = "1995";
        String userSubject = "Computer Science";
        String currentAddress = "Address";
        String userHobbies = "Reading";
        String userState = "Uttar Pradesh";
        String userCity = "Agra";



        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(userGender)).click();
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


        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName), text(lastName),
                text(userEmail), text(userNumber));
    }
}
