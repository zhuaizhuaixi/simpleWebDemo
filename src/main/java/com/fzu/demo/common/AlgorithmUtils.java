package com.fzu.demo.common;

import com.fzu.demo.web.entity.GameEntity;
import com.fzu.demo.web.entity.TagEntity;
import com.fzu.demo.web.entity.UserEntity;

import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author zzx
 *         Created by zzx on 2017/11/28.
 */
public class AlgorithmUtils {
    public static double recommendationAlgorithmBaseOnContent(List<TagEntity> userTag, List<TagEntity> gameTag) {
        //创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数
        Map<String, int[]> vectorSpace = new HashMap<>(50);
        //为了避免频繁产生局部变量，所以将itemCountArray声明在此
        int[] itemCountArray;

        for (TagEntity tag : userTag) {
            String tagName = tag.getName();
            if (!vectorSpace.containsKey(tagName)) {
                itemCountArray = new int[2];
                itemCountArray[0] = 1;
                itemCountArray[1] = 0;
                vectorSpace.put(tagName, itemCountArray);
            } else {
                ++(vectorSpace.get(tagName)[0]);
            }
        }

        for (TagEntity tag : gameTag) {
            String tagName = tag.getName();
            if (vectorSpace.containsKey(tagName)) {
                ++(vectorSpace.get(tagName)[1]);
            } else {
                itemCountArray = new int[2];
                itemCountArray[0] = 0;
                itemCountArray[1] = 1;
                vectorSpace.put(tagName, itemCountArray);
            }
        }

        //计算相似度
        //向量1的模
        double vector1Modulo = 0.00;
        //向量2的模
        double vector2Modulo = 0.00;
        //向量积
        double vectorProduct = 0.00;

        for (Object o : vectorSpace.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            itemCountArray = (int[]) entry.getValue();

            vector1Modulo += itemCountArray[0] * itemCountArray[0];
            vector2Modulo += itemCountArray[1] * itemCountArray[1];

            vectorProduct += itemCountArray[0] * itemCountArray[1];
        }

        vector1Modulo = Math.sqrt(vector1Modulo);
        vector2Modulo = Math.sqrt(vector2Modulo);

        //返回相似度
        return (vectorProduct / (vector1Modulo * vector2Modulo));
    }

    public static void collaborativeFilteringRecommendation(List<UserEntity> recommendUsers, Integer recommendUserID) {
        System.out.println("Input the total users number:");
        //输入用户总量
        int n = recommendUsers.size(), i = 0;
        //建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        int[][] sparseMatrix = new int[n][n];
        //存储每一个用户对应的不同物品总数  eg: A 3
        Map<Integer, Integer> userItemLength = new HashMap<>(50);
        //建立物品到用户的倒排表 eg: a A B
        Map<Integer, Set<Integer>> itemUserCollection = new HashMap<>(50);
        //辅助存储物品集合
        Set<Integer> items = new HashSet<>();
        //辅助存储每一个用户的用户ID映射
        Map<Integer, Integer> userID = new HashMap<>(50);
        //辅助存储每一个ID对应的用户映射
        Map<Integer, Integer> idUser = new HashMap<>(50);
        System.out.println("Input user-items mapping information:<eg:A a b d>");

        //依次处理N个用户 输入数据  以空格间隔
        for (UserEntity user : recommendUsers) {
            //eg: A 3
            userItemLength.put(user.getId(), user.getGames().size());
            //用户ID与稀疏矩阵建立对应关系
            userID.put(user.getId(), i);
            idUser.put(i++, user.getId());
            //建立物品--用户倒排表
            for (GameEntity game : user.getGames()) {
                //如果已经包含对应的物品--用户映射，直接添加对应的用户
                if (items.contains(game.getId())) {
                    itemUserCollection.get(game.getId()).add(user.getId());
                } else {//否则创建对应物品--用户集合映射
                    items.add(game.getId());
                    //创建物品--用户倒排关系
                    itemUserCollection.put(game.getId(), new HashSet<>());
                    itemUserCollection.get(game.getId()).add(user.getId());
                }
            }
        }

        System.out.println(itemUserCollection.toString());
        //计算相似度矩阵【稀疏】
        Set<Entry<Integer, Set<Integer>>> entrySet = itemUserCollection.entrySet();
        for (Entry<Integer, Set<Integer>> anEntrySet : entrySet) {
            Set<Integer> commonUsers = anEntrySet.getValue();
            for (Integer userU : commonUsers) {
                for (Integer userV : commonUsers) {
                    if (!userU.equals(userV)) {
                        //计算用户u与用户v都有正反馈的物品总数
                        sparseMatrix[userID.get(userU)][userID.get(userV)] += 1;
                    }
                }
            }
        }
        System.out.println(userItemLength.toString());
        System.out.println("Input the user for recommendation:<eg:A>");
        System.out.println(userID.get(recommendUserID));
        //计算用户之间的相似度【余弦相似性】
        int recommendUserId = userID.get(recommendUserID);
        for (int j = 0; j < sparseMatrix.length; j++) {
            if (j != recommendUserId) {
                System.out.println(idUser.get(recommendUserId) + "--" + idUser.get(j) + "相似度:" + sparseMatrix[recommendUserId][j] / Math.sqrt(userItemLength.get(idUser.get(recommendUserId)) * userItemLength.get(idUser.get(j))));
            }
        }

        //计算指定用户recommendUser的物品推荐度
        //遍历每一件物品
        for (Integer item : items) {
            //得到购买当前物品的所有用户集合
            Set<Integer> users = itemUserCollection.get(item);
            //如果被推荐用户没有购买当前物品，则进行推荐度计算
            if (!users.contains(recommendUserID)) {
                double itemRecommendDegree = 0.0;
                for (Integer user : users) {
                    //推荐度计算
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUserID)][userID.get(user)] / Math.sqrt(userItemLength.get(recommendUserID) * userItemLength.get(user));
                }
                System.out.println("The item " + item + " for " + recommendUserID + "'s recommended degree:" + itemRecommendDegree);
            }
        }
    }
}
