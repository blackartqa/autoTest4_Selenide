package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @Test
    void shouldDeliveryRequestSuccess(){

        String dateOfMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Санкт-Петербург");
        $("[placeholder='Дата встречи']").setValue(dateOfMeeting);
        $("[name='name']").setValue("Чернышов Артем");
        $("[name='phone']").setValue("+75555555555");
        $(".checkbox__box").click();
        $(byText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofMillis(15000));
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateOfMeeting));

    }
}
