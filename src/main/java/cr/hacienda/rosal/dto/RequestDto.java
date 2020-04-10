package cr.hacienda.rosal.dto;

public class RequestDto {
    private int id;

    private UserDto userDto;

    private String message;

    private String response;

    private int type;

    private String publishDate;

    private String state;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "userDto=" + userDto +
                ", message='" + message + '\'' +
                ", response='" + response + '\'' +
                ", type=" + type +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
