package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PaymentGatePage {
    private final SelenideElement paymentGate = $(byText("Оплата по карте"));
    private final ElementsCollection fieldForm = $$(".input__control");
    private final SelenideElement fieldCardNumber = $(" .input__top");
    private final SelenideElement fieldMonth = $(" .input__top");
    private final SelenideElement fieldYear = $(" .input__top");
    private final SelenideElement fieldName = $(" .input__top");
    private final SelenideElement fieldCardCVC = $(" .input__top");
    private final ElementsCollection buttonContinue = $$(byText("Продолжить"));
    private final SelenideElement notification = $(".notification__content");
    private final SelenideElement notificationSuccessful = $(".notification_status_ok");
    private final SelenideElement notificationUnsuccessful = $(".notification_status_error");
    private final SelenideElement fieldMistake = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement formatMistake = $(byText("Неверный формат"));
    private final SelenideElement expiredCardMistake = $(byText("Истёк срок действия карты"));

    public void fillForm(String form, String value){
        fieldForm.findBy(text(form)).$("input").setValue(value);
    }
    public void clickButton(String button) {
        buttonContinue.findBy(text(button)).click();
    }
    public void cleanForm(String form) {
        fieldForm.findBy(text(form)).$("input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }
    public PaymentGatePage() {
        paymentGate
                .shouldBe(visible);
    }

    public void seeSuccessful() {
        notificationSuccessful
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void seeUnsuccessful() {
        notificationUnsuccessful
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void seeFieldMistake(String expectedText) {
        fieldMistake.shouldHave(exactText(expectedText)).shouldBe(Condition.visible);
    }

    public void seeFormatMistake(String expectedText) {
        formatMistake.shouldHave(exactText(expectedText)).shouldBe(Condition.visible);
    }

    public void seeExpiredCardMistake(String expectedText) {
        expiredCardMistake.shouldHave(exactText(expectedText)).shouldBe(Condition.visible);
    }
}
