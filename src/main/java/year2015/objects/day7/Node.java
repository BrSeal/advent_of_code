package year2015.objects.day7;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Node {
    Short value;
    NodeType type;
    List<Node> previous;
}
