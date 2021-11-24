package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.lanit.at.web.pagecontext.PageManager;
import ru.lanit.at.utils.Sleep;

import static com.codeborne.selenide.Selenide.*;
import static steps.DropDownEnum.ДОРОЖЕ;


public class WebActionSteps {

    private PageManager pageManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebActionSteps.class);

    public WebActionSteps(PageManager manager) {
        this.pageManager = manager;
    }

    private final SelenideElement searchField = $x("//*[@class=\"input-input-Zpzc1\"]");
    private final SelenideElement placeForEnteringRegion = $x("//input[@placeholder='Город, регион или Россия']");
    private final SelenideElement sortDropDown = $x("//div[@class='select-select-box-jJiQW select-size-s-VX5kS']");


    /**
     * нажимает на элемент по тексту
     *
     * @param text текст элемента
     */
    @Когда("кликнуть на элемент по тексту {string}")
    public void clickElementWithText(String text) {
        $(Selectors.byText(text))
                .shouldBe(Condition.visible)
                .click();
        LOGGER.info("клик на элемент по тексту '{}'", text);
    }

    @Если("кликнуть на элемент {string}")
    public void clickOnElement(String elementName) {
        SelenideElement element = pageManager
                .getCurrentPage()
                .getElement(elementName);
        element
                .shouldBe(Condition.visible)
                .click();
        LOGGER.info("клик на элемент '{}'", elementName);
    }

    /**
     * скролл до элемента
     *
     * @param elementName наименование элемента
     */
    @Когда("проскроллить страницу до элемента {string}")
    public void scrollToElement(String elementName) {
        SelenideElement element = pageManager.getCurrentPage().getElement(elementName);
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до элемента '{}'", elementName);
    }

    /**
     * скролл до текста
     *
     * @param text текст
     */
    @Когда("проскроллить страницу до текста {string}")
    public void scrollToText(String text) {
        SelenideElement element = $(Selectors.byText(text));
        element.shouldBe(Condition.visible)
                .scrollIntoView("{block: 'center'}");
        LOGGER.info("скролл страницы до текста '{}'", text);
    }

    @И("подождать {int} сек")
    public void waitSeconds(int timeout) {
        Sleep.pauseSec(timeout);
    }

    /**
     * Ввод значения в элемент
     *
     * @param field - наименование элемента
     * @param value - значение
     */
    @Когда("ввести в поле {string} значение {string}")
    public void fillTheField(String field, String value) {
        SelenideElement fieldElement = pageManager
                .getCurrentPage()
                .getElement(field);
        fieldElement
                .shouldBe(Condition.visible)
                .setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", field, value);
    }

    /**
     * Очистка поля
     *
     * @param elementName наименование элемента
     */
    @Если("очистить поле {string}")
    public void clearFiled(String elementName) {
        pageManager
                .getCurrentPage()
                .getElement(elementName)
                .shouldBe(Condition.visible)
                .clear();
    }

    @Given("открыта страница Avito")
    public void mainPageIsOpen() {
        Selenide.open("https://www.avito.ru/");

    }

    @And("в поле поиска введено {string}")
    public void valueEnteredInSearchField(String string) {
        searchField.click();
        searchField.setValue(string);
    }

    @Then("значение {string} введено в поле поиска региона")
    public void valueIsEnteredInTheRegionField(String string) {
        placeForEnteringRegion.click();
        placeForEnteringRegion.setValue(string);
        sleep(2000);
    }

    @Then("страница с результатами поиска открыта")
    public void pageSearchResultsIsOpen() {
    }

    @ParameterType("[A-Za-zA-Яа-я ]+")
    public DropDownEnum option(String str) {
        return DropDownEnum.valueOf(str);
    }

    @Тогда("значение {option} выбрано из выпадающего сортировочного списка")
    public void значениеДОРОЖЕВыбраноИзВыпадающегоСортировочногоСписка(DropDownEnum option) {
        sortDropDown.click();
        String xpath = String.format("//option[contains(text(),'%s')]", option.getOption());
        SelenideElement dinamicXpath = $x(xpath);
        dinamicXpath.click();

    }
}
