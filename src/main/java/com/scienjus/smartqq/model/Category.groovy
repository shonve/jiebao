package com.scienjus.smartqq.model
/**
 * 分组
 * @author ScienJus
 * @date 15/12/19.
 */
public class Category {

    int index;

    int sort;

    String name;

    List<Friend> friends = new ArrayList<>();

    public void addFriend(Friend friend) {
        this.friends.add(friend);
    }

    @Override
    public String toString() {
        return "Category{" +
                "index=" + index +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", friends=" + friends +
                '}';
    }

    public static Category defaultCategory() {
        Category category = new Category();
        category.setIndex(0);
        category.setSort(0);
        category.setName("我的好友");
        return category;
    }
}
