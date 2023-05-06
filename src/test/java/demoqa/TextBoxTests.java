package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;



public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {

        String userName = "John Doe";

        open("/text-box");
        $(".main-header").shouldHave(text("Text box"));

        $("#userName").setValue(userName);
        $("#userEmail").setValue("johndoe@test.com");
        $("#currentAddress").setValue("Some address");
        $("#permanentAddress").setValue("Other address");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output").$("#name").shouldHave(text(userName));
        $("#output #email").shouldHave(text("johndoe@test.com"));
    }
}
