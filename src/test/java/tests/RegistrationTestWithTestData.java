package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class RegistrationTestWithTestData extends TestData{

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void setUp() {

//        currentAddress = getNewCurrentAddress();
//        userName = getNewUserName();
//        userEmail = getNewEmail();
    }

    @Test
    void successfulRegistrationTest() {

        String currentAddress = "Some address";
        String userName = "John Doe";
        String userEmail = "Johndoe@email.test";


        open("/text-box");
        $(".main-header").shouldHave(text("Text box"));

        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output").$("#name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(userEmail));
    }
}
