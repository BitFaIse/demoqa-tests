package tests;

import com.github.javafaker.Faker;
import data.Genders;
import data.Subjects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.GenerateRandom.cityGenerator;

public class AutomationPracticeFormWithDriver extends TestBaseExtended {

    @Test
    @Tag("remote")
    void testRegistrationForm() {

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userEmail = faker.internet().emailAddress();
        Genders genders = faker.options().option(Genders.values());
        String userNumber = faker.phoneNumber().subscriberNumber(10);
        String birthDay = String.format("%02d", faker.number().numberBetween(1, 28));
        String birthMonth = faker.options().option("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        String birthYear = faker.number().numberBetween(1900, 2022) + "";
        Subjects subjects = faker.options().option(Subjects.values());
        String currentAddress = faker.address().fullAddress();
        String userHobbies = faker.options().option("Sports", "Reading", "Music");
        String userState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        String userCity = cityGenerator(userState);

        step("Открываем и заполняем форму", () -> {
            registrationPage.openPage()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender(genders)
                    .setNumber(userNumber)
                    .setBirthDate(birthDay, birthMonth, birthYear)
                    .setSubject(subjects)
                    .setPicture("src/test/resources/picture.jpg")
                    .setAddress(currentAddress)
                    .setHobbies(userHobbies)
                    .setStateAndCity(userState, userCity)
                    .pressSubmit();
        });

        step("Проверяем заполненную форму", () -> {
            registrationPage.verifyResultsModalAppears()
                    .verifyResult("Student Name", firstName + " " + lastName)
                    .verifyResult("Student Email", userEmail)
                    .verifyResult("Gender", genders.toString())
                    .verifyResult("Mobile", userNumber)
                    .verifyResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear);
        });
    }
}
