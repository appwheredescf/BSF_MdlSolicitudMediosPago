package mx.appwhere.mediospago.front.application.dto.ejemplo;

import org.hibernate.validator.constraints.Length;

import mx.appwhere.mediospago.front.application.constants.ApplicationConstants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Luis Angel Flores
 * @version 1.0 20/02/2018
 */
public class ExampleDto {
    @NotNull(message = ApplicationConstants.NULL_MESSAGE)
    @Length(min = 2, message = ApplicationConstants.LENGTH_MESSAGE)
    private String name;
    @NotNull(message = ApplicationConstants.NULL_MESSAGE)
    @Min(value = 18, message = ApplicationConstants.MIN_MESSAGE)
    @Max(value = 150, message = ApplicationConstants.MAX_MESSAGE)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
