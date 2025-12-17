package enums;

public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String value;

    private TaskPriority(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
