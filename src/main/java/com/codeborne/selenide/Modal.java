package com.codeborne.selenide;

import com.codeborne.selenide.ex.DialogTextMismatch;
import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.Alert;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Modal {
  private final Driver driver;

  public Modal(Driver driver) {
    this.driver = driver;
  }

  public String confirm() {
    return confirm(null);
  }

  public String confirm(@Nullable String expectedDialogText) {
    Alert alert = driver.switchTo().alert();
    String actualDialogText = alert.getText();
    alert.accept();
    checkDialogText(driver, expectedDialogText, actualDialogText);
    return actualDialogText;
  }

  public String prompt() {
    return prompt(null, null);
  }

  public String prompt(@Nullable String inputText) {
    return prompt(null, inputText);
  }

  public String prompt(@Nullable String expectedDialogText, @Nullable String inputText) {
    Alert alert = driver.switchTo().alert();
    String actualDialogText = alert.getText();
    if (inputText != null)
      alert.sendKeys(inputText);
    alert.accept();
    checkDialogText(driver, expectedDialogText, actualDialogText);
    return actualDialogText;
  }

  public String dismiss() {
    return dismiss(null);
  }

  public String dismiss(@Nullable String expectedDialogText) {
    Alert alert = driver.switchTo().alert();
    String actualDialogText = alert.getText();
    alert.dismiss();
    checkDialogText(driver, expectedDialogText, actualDialogText);
    return actualDialogText;
  }

  private static void checkDialogText(Driver driver, @Nullable String expectedDialogText, String actualDialogText) {
    if (expectedDialogText != null && !expectedDialogText.equals(actualDialogText)) {
      DialogTextMismatch assertionError = new DialogTextMismatch(driver, actualDialogText, expectedDialogText);
      throw UIAssertionError.wrap(driver, assertionError, driver.config().timeout());
    }
  }
}
