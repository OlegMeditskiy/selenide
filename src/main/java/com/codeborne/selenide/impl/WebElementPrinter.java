package com.codeborne.selenide.impl;

import com.codeborne.selenide.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface WebElementPrinter {
  @CheckReturnValue
  @Nonnull
  String describe(Driver driver, @Nullable WebElement element);

  @CheckReturnValue
  @Nonnull
  default String selector(By selector) {
    return selector.toString()
      .replaceFirst("By\\.selector:\\s*", "")
      .replaceFirst("By\\.cssSelector:\\s*", "");
  }

  @CheckReturnValue
  @Nonnull
  default String shortly(By selector) {
    if (selector instanceof By.ByCssSelector) {
      return selector.toString()
        .replaceFirst("By\\.selector:\\s*(.*)", "$1")
        .replaceFirst("By\\.cssSelector:\\s*(.*)", "$1");
    }
    return selector.toString();
  }

  @CheckReturnValue
  @Nonnull
  String shortly(Driver driver, @Nonnull WebElement element);
}
