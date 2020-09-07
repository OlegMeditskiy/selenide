package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.Cleanup;
import com.codeborne.selenide.impl.Plugins;
import com.codeborne.selenide.impl.WebElementPrinter;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.WebDriverException;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ToString implements Command<String> {
  private final WebElementPrinter printer = Plugins.getWebElementPrinter();

  @Override
  @CheckReturnValue
  @Nonnull
  public String execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
    try {
      return printer.describe(locator.driver(), locator.getWebElement());
    } catch (WebDriverException elementDoesNotExist) {
      return Cleanup.of.webdriverExceptionMessage(elementDoesNotExist);
    } catch (ElementNotFound elementDoesNotExist) {
      return elementDoesNotExist.getMessage();
    } catch (IndexOutOfBoundsException elementDoesNotExist) {
      return elementDoesNotExist.toString();
    }
  }
}
