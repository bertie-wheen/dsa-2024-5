package dsa.lib;

import org.junit.jupiter.api.IndicativeSentencesGeneration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@IndicativeSentencesGeneration(
  separator = " ",
  generator = ReplaceCamelCaseDisplayNameGenerator.class)
public @interface DefaultDisplayNameGeneration
{

}
