package year2015.objects.day7;

public enum NodeType {
    CONSTANT,
    NOT,
    AND,
    OR,
    RSHIFT,
    LSHIFT;

    public static NodeType fromString(String str){
        for(NodeType value : values()){
            if(str.contains(value.name())){
                return value;
            }
        }
        throw new IllegalArgumentException("Not found NodeType for string: " + str);
    }
}
