package common.treenode;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class InfiniteTree<M extends BaseMapper<X>, X> {
    private final M mapper;

    public InfiniteTree(M mapper) {
        this.mapper = mapper;
    }


    /**
     * 条件构造器临时变量，存放输入的条件构造器
     */
    private QueryWrapper<X> qwTmp = new QueryWrapper<>();

    /**
     * 首字母大写方法
     */
    private String toU(String a) {
        return a.substring(0, 1).toUpperCase() + a.substring(1);
    }

    /**
     * 获取子节点的条件构造器
     */
    private QueryWrapper<X> getQw() {
        //TODO 把临时变量赋值给使用的条件构造器
        return qwTmp.clone();
    }

    /**
     * 设置条件构造器
     *
     * @param qwNode 条件构造器
     */
    public void setQW(QueryWrapper<X> qwNode) {
        this.qwTmp = qwNode;
    }

    private List<TreeNode> createTree(List<TreeNode> treeNodeList, List<X> list, int n,
                                      String column, String methodName,
                                      String treeValueName, String treeLabelName,
                                      String nodeValueName, String nodeLabelName) {
        if (!list.isEmpty()) {
            n++;
            for (X x : list) {
                QueryWrapper<X> qwNode = getQw();
                List<TreeNode> node = new ArrayList<>();
                try {
                    qwNode.eq(column, x.getClass().getMethod("get" + toU(methodName)).invoke(x));
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                List<X> nodeList = mapper.selectList(qwNode);
                createTree(node, nodeList, n, column, methodName, treeValueName, treeLabelName, nodeValueName, nodeLabelName);
                try {
                    //TODO 判断是否为根节点
                    if (n % 2 == 1) {
                        treeNodeList.add(new TreeNode(
                                x.getClass().getMethod("get" + toU(treeValueName)).invoke(x).toString(),
                                x.getClass().getMethod("get" + toU(treeLabelName)).invoke(x).toString(),
                                node));
                    } else {
                        treeNodeList.add(new TreeNode(
                                x.getClass().getMethod("get" + toU(nodeValueName)).invoke(x).toString(),
                                x.getClass().getMethod("get" + toU(nodeLabelName)).invoke(x).toString(),
                                node));
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return treeNodeList;
    }

    /**
     * @param list       根节点列表
     * @param column     子节点判断条件的列的名称
     * @param methodName 子节点判断条件的上级节点的属性名称
     * @param valueName  树形value对应的属性名
     * @param labelName  树形label对应的属性名
     */
    public List<TreeNode> getTreeNodeList(List<X> list,
                                          String column, String methodName,
                                          String valueName, String labelName) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        return createTree(treeNodeList, list, 0, column, methodName, valueName, labelName, valueName, labelName);
    }

    /**
     * 二级树形
     *
     * @param list          根节点列表
     * @param column        子节点判断条件的列的名称
     * @param methodName    子节点判断条件的上级节点的属性名称
     * @param treeValueName 树形根节点value对应的属性名
     * @param treeLabelName 树形根节点label对应的属性名
     * @param nodeValueName 树形子节点value对应的属性名
     * @param nodeLabelName 树形子节点label对应的属性名
     */
    public List<TreeNode> getTreeNodeList(List<X> list,
                                          String column, String methodName,
                                          String treeValueName, String treeLabelName,
                                          String nodeValueName, String nodeLabelName) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        return createTree(treeNodeList, list, 0, column, methodName, treeValueName, treeLabelName, nodeValueName, nodeLabelName);
    }

}
