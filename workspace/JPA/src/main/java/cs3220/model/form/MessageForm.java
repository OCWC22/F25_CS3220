package cs3220.model.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageForm {

    @NotBlank(message = "Message is required")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
