package com.filetool.main;

import com.filetool.util.FileUtil;
import com.filetool.util.LogUtil;
import com.routesearch.route.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * 工具入口
 * 
 * @author
 * @since 2016-3-1
 * @version v1.0
 */
public class Main
{
    public static Map<Integer, Vector<Node>> graph = null;
    public Vector<Node> nodes = null;
    public static HashMap v;
    static Vector<Integer> as;
    int a = 5;
    public static List<Integer> list = null;
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.err.println("please input args: graphFilePath, conditionFilePath, resultFilePath");
            return;
        }

        String graphFilePath = args[0];
        String conditionFilePath = args[1];
        String resultFilePath = args[2];

        LogUtil.printLog("Begin");

        // 读取输入文件
        String graphContent = FileUtil.read(graphFilePath, null);
        String conditionContent = FileUtil.read(conditionFilePath, null);
        String[] conditionContents = conditionContent.split("/n");
        for(String s :conditionContents){
            String[] splits = s.split(",");
            if (graph.containsKey(Integer.valueOf(splits[1]))) {
                Vector<Node> nodes = graph.get(Integer.valueOf(splits[1]));
                insertToGraph(nodes, new Node(Integer.valueOf(splits[2]), Integer.valueOf(splits[3])));
                graph.put(Integer.valueOf(splits[1]), nodes);
            } else {
                Vector<Node> nodes = new Vector<>();
                nodes.add(new Node(Integer.valueOf(splits[2]), Integer.valueOf(splits[3])));
                graph.put(Integer.valueOf(splits[1]), nodes);
            }
        }
        // 功能实现入口
        String resultStr = Route.searchRoute(graphContent, conditionContent);

        // 写入输出文件
        FileUtil.write(resultFilePath, resultStr, false);

        LogUtil.printLog("End");
    }

    public static int getSplitIndex(Vector<Node> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (!list.contains(vector.get(i).val)) {
                return i;
            }
        }
        return vector.size();
    }

    public static void insertToGraph(Vector<Node> vector, Node node) {
        for (Node node1 : vector) {
            if (node1.val == node.val) {
                if (node1.weight < node.weight) {
                    return;
                } else {
                    vector.remove(node1);
                    break;
                }
            }
        }
        int splitIndex = getSplitIndex(vector);
        if (list.contains(node.val)) {
            boolean insert = false;
            for (int i = 0; i < splitIndex; i++) {
                if (node.weight < vector.get(i).weight) {
                    vector.add(i, node);
                    insert = true;
                    break;
                }
            }
            if (!insert) {
                vector.add(splitIndex, node);
            }

        } else {
            boolean insert = false;
            for (int i = splitIndex; i < vector.size(); i++) {
                if (node.weight < vector.get(i).weight) {
                    vector.add(i, node);
                    insert = true;
                    break;
                }
            }
            if (!insert)
                vector.add(node);
        }
    }

}
