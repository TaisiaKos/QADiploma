package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private static SelenideElement elementCard = $(byText("Купить"));
    private static SelenideElement elementCredit = $(byText("Купить в кредит"));

    public void DashboardPageVisible() {
        heading.shouldHave(Condition.text("Путешествие дня")).shouldBe(visible);
    }

    public PaymentGatePage selectCard() {
        elementCard.click();
        return new PaymentGatePage();
    }

    public CreditGatePage selectCredit() {
        elementCredit.click();
        return new CreditGatePage();
    }
}

