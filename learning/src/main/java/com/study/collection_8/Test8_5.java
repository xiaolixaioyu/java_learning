package com.study.collection_8;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
因此，正确使用Map必须保证：
作为key的对象必须正确覆写equals()方法，相等的两个key实例调用equals()必须返回true；
作为key的对象还必须正确覆写hashCode()方法，且hashCode()方法要严格遵循以下规范：
如果两个对象相等，则两个对象的hashCode()必须相等；
如果两个对象不相等，则两个对象的hashCode()尽量不要相等。
 */
class Teacher{
    String firstName;
    String lastName;
    int age;
    @Override
    //将它作为key需要重写equal方法
    public boolean equals(Object o){
        if(o instanceof Teacher){
            //如果左边是右边这个对象
            return Objects.equals(this.firstName,((Teacher)o).firstName)&&Objects.equals(this.lastName,((Teacher)o).lastName)&&((Teacher)o).age==this.age;
        }else{
            return false;
        }
    }
    //并且我们还要重写他的hashcode
    @Override
    public int hashCode(){
        int h = 0;
        h = 31 * h + firstName.hashCode();
        h = 31 * h + lastName.hashCode();
        h = 31 * h + age;
        return h;
    }
}
public class Test8_5 {
    public static void main(String[] args) {
        String key1="a";
        Map<String,String> map1=new HashMap<>();
        map1.put(key1,"b");
        String key2=new String("a");
        System.out.println("使用同样的内容的key  可以得到同样的value");
        System.out.println(map1.get(key2));
        System.out.println("但是两个对象的并不是同一个对象");
        System.out.println(key1==key2);
        System.out.println("map重写了equal方法比较值是否相等");
        System.out.println(key1.equals(key2));

    }
}

