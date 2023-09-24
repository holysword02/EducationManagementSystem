package common.treenode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    private String value;
    private String label;
    private List<TreeNode> children;
}