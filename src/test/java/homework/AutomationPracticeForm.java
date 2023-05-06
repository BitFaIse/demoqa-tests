package homework;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        File file = new File("src/main/resources/picture.jpg");

        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue("some-email@test.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("77777777777");
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue("Address");
        $(byText("Reading")).click();
        $("#state").click();
        $(byText("Uttar Pradesh")).click();
        $("#city").click();
        $(byText("Agra")).click();
        $("#submit").pressEnter();
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
    }
}
